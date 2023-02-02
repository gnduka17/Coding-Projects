#include <getopt.h>  // getopt, optarg
#include <stdlib.h>  // exit, atoi, malloc, free
#include <stdio.h>   // printf, fprintf, stderr, fopen, fclose, FILE
#include <limits.h>  // ULONG_MAX
#include <string.h>  // strcmp, strerror
#include <errno.h>   // errno

/* fast base-2 integer logarithm */
#define INT_LOG2(x) (31 - __builtin_clz(x))
#define NOT_POWER2(x) (__builtin_clz(x) + __builtin_ctz(x) != 31)

/* tag_bits = ADDRESS_LENGTH - set_bits - block_bits */
#define ADDRESS_LENGTH 64

/**
 * Print program usage (no need to modify).
 */
static void print_usage() {
    printf("Usage: csim [-hv] -S <num> -K <num> -B <num> -p <policy> -t <file>\n");
    printf("Options:\n");
    printf("  -h           Print this help message.\n");
    printf("  -v           Optional verbose flag.\n");
    printf("  -S <num>     Number of sets.           (must be > 0)\n");
    printf("  -K <num>     Number of lines per set.  (must be > 0)\n");
    printf("  -B <num>     Number of bytes per line. (must be > 0)\n");
    printf("  -p <policy>  Eviction policy. (one of 'FIFO', 'LRU')\n");
    printf("  -t <file>    Trace file.\n\n");
    printf("Examples:\n");
    printf("  $ ./csim    -S 16  -K 1 -B 16 -p LRU -t traces/yi.trace\n");
    printf("  $ ./csim -v -S 256 -K 2 -B 16 -p LRU -t traces/yi.trace\n");
}

/* Parameters set by command-line args (no need to modify) */
int verbose = 0;   // print trace if 1
int S = 0;         // number of sets
int K = 0;         // lines per set
int B = 0;         // bytes per line

typedef enum { FIFO = 1, LRU = 2 } Policy;
Policy policy;     // 0 (undefined) by default

FILE *trace_fp = NULL;
int bBits;
int sBits;

/**
 * Parse input arguments and set verbose, S, K, B, policy, trace_fp.
 *
 * TODO: Finish implementation
 */
static void parse_arguments(int argc, char **argv) {
    char c;
    while ((c = getopt(argc, argv, "S:K:B:p:t:vh")) != -1) {
        switch(c) {
            case 'S':
                S = atoi(optarg);
                if (NOT_POWER2(S)) {
                    fprintf(stderr, "ERROR: S must be a power of 2\n");
                    exit(1);
                }
                break;
            case 'K':
               K = atoi(optarg);
                break;
            case 'B':
               B = atoi(optarg);
                break;
            case 'p':
                if (!strcmp(optarg, "FIFO")) {
                    policy = FIFO;
                }
                else if(!strcmp(optarg, "LRU")){
                    policy = LRU;
                }
                else{
                    fprintf(stderr, "ERROR: unknown policy");
                    exit(1);
                }
                break;
            case 't':
                trace_fp = fopen(optarg, "r");
                if (!trace_fp) {
                    fprintf(stderr, "ERROR: %s: %s\n", optarg, strerror(errno));
                    exit(1);
                }
                break;
            case 'v':
                verbose = 1;
                break;
            case 'h':
                // TODO
                exit(0);
            default:
                print_usage();
                exit(1);
        }
    }

    /* Make sure that all required command line args were specified and valid */
    if (S <= 0 || K <= 0 || B <= 0 || policy == 0 || !trace_fp) {
        printf("ERROR: Negative or missing command line arguments\n");
        print_usage();
        if (trace_fp) {
            fclose(trace_fp);
        }
        exit(1);
    }

    /* Other setup if needed */
    bBits = INT_LOG2(B);
    sBits = INT_LOG2(S);



}

/**
 * Cache data structures
 * TODO: Define your own!
 */
    struct Line{
        int validBit;
        unsigned long tag;
        int metaData;
    };
     struct Line **cache;
     void dataLSM(unsigned long a, int f);
    



/**
 * Allocate cache data structures.
 *
 * This function dynamically allocates (with malloc) data structures for each of
 * the `S` sets and `K` lines per set.
 *
 * TODO: Implement
 */
static void allocate_cache() {
    //printf("first line in allocate func");
   
    cache = (struct Line **)malloc(sizeof(struct Line *)*S);
    for(int i=0; i<S; i++){
        cache[i] = (struct Line *)malloc(sizeof(struct Line)*K);
    }
    //setting vars to 0
    for(int j=0; j<S; j++){
        for(int l=0; l<K; l++){
            cache[j][l].validBit = 0;
            cache[j][l].tag=0;
            cache[j][l].metaData = 0;
        }
    }



}

/**
 * Deallocate cache data structures.
 *
 * This function deallocates (with free) the cache data structures of each
 * set and line.
 *
 * TODO: Implement
 */
static void free_cache() {
    //printf("first line in fress cahce func");
    for(int i = 0; i<S; i++){
        if(cache[i]!=NULL){
            free(cache[i]);
        } 
    }
    if(cache!=NULL){
        free(cache);
    }
    



}

/* Counters used to record cache statistics */
int miss_count     = 0;
int hit_count      = 0;
int eviction_count = 0;
int counter = 0;
int hit;

/**
 * Simulate a memory access.
 *
 * If the line is already in the cache, increase `hit_count`; otherwise,
 * increase `miss_count`; increase `eviction_count` if another line must be
 * evicted. This function also updates the metadata used to implement eviction
 * policies (LRU, FIFO).
 *
 * TODO: Implement
 */
static void access_data(unsigned long addr) {
    //printf("Access to %016lx\n", addr);
    unsigned long tagIn;
    unsigned long in;
    int update = 0;
    int ta;
    int fill = 0;
    hit = 0;
    counter++;
    ta = ADDRESS_LENGTH - (bBits+sBits);
    tagIn = addr >> (bBits+sBits);
    //find index of set with size of tag
    in = (addr << (ta)) >>(bBits+ta);
    //in = (addr>>bBits) & S;
    //printf("first line in access data func");

    for(int i=0;i<K;i++){
       // printf("forloop");
        //hit
        if(cache[in][i].validBit &&cache[in][i].tag == tagIn){
            hit=1;
            hit_count++;
             //printf("hitcount up by 1");
            if(policy==LRU){
                cache[in][i].metaData = counter;
            }

        }
    }
    //miss with no evict
    for(int i=0; i<K;i++){
        if(!cache[in][i].validBit &&!hit){
            fill = 1;
            break;
        }
    }
    if(fill){
        miss_count++;
        //printf("misscount up by 1");
        for(int i=0; i<K;i++){
            if(!cache[in][i].validBit){
                update=i;
                break;
            }
        }
        cache[in][update].tag=tagIn;
        cache[in][update].validBit=1;
        cache[in][update].metaData = counter;
    }else if(!hit){
        int holder=0;
        int ind=0;
        int temp = 0;
        eviction_count++;
        //printf("eviction and misscount up by 1");
         miss_count++;
         temp = cache[in][0].metaData;
         for(int i=0; i<K;i++){
             if(cache[in][i].metaData<temp){
                 holder = i;
                 temp = cache[in][i].metaData;
             }
         }
         ind =holder;
         cache[in][ind].metaData = counter;
         cache[in][ind].tag=tagIn;
    }
}

/**
 * Replay the input trace.
 *
 * This function:
 * - reads lines (e.g., using fgets) from the file handle `trace_fp` (a global variable)
 * - skips lines not starting with ` S`, ` L` or ` M`
 * - parses the memory address (unsigned long, in hex) and len (unsigned int, in decimal)
 *   from each input line
 * - calls `access_data(address)` for each access to a cache line
 *
 * TODO: Implement
 */
void dataLSM(unsigned long a, int f){
    access_data(a);
    for(int i=1; i<f;i++){
        if(!((a+i)%B)){
            access_data(a+i);
        }
    }
}
static void replay_trace() {

    char letter;
    unsigned long addy;
    int size;
    //printf("first line in replay trace func");
    if(trace_fp!=NULL){
        //printf("sec line in side not null if ");
        while(fscanf(trace_fp, " %c %lx,%d", &letter, &addy, &size)==3){
            //printf("INSIDE WHILE PARSING:");
           // printf("%c", letter);
            switch (letter)
            {
                
                case 'L':
                //printf("INSIDE L!!!!");
                    dataLSM(addy, size);
                    break;
                case 'S':
                    dataLSM(addy, size);
                    break;
                case 'M':
                    dataLSM(addy, size);
                    dataLSM(addy, size);
                    break;
                default:
                    break;

            }
        }
    }
}

/**
 * Print cache statistics (DO NOT MODIFY).
 */
static void print_summary(int hits, int misses, int evictions) {
    printf("hits:%d misses:%d evictions:%d\n", hits, misses, evictions);
}

int main(int argc, char **argv) {
    parse_arguments(argc, argv);  // set global variables used by simulation
    allocate_cache();             // allocate data structures of cache
    replay_trace();               // simulate the trace and update counts
    free_cache();                 // deallocate data structures of cache
    fclose(trace_fp);             // close trace file
    print_summary(hit_count, miss_count, eviction_count);  // print counts
    return 0;
}
