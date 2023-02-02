# USC CS356 Fall 2020: Hello Lab (v1.0)


**Q1.** If you run `./hello > out.txt` the messages "Hello ... !" are
redirected to `out.txt` but the instructions are not. Why?

Your Answer: The instructions are being printed to stderr using fprintf unlike the text "Hello!..." that is used with the printf method. Stderr is not directed to out.txt.
```
(Suggestion: Look at the `printf` statements in `hello.c`)

```


**Q2.** How can you redirect both `stdout` and `stderr` to `out.txt`?

Your Answer: with this line-> find /var/tmp -name "*" -print > out.txt 2>&1
```
(Suggestion: The answer is in README.md)

```


**Q3.** The file `in.txt` contains the input for `hello`. How can you make
`hello` read from `in.txt` instead of `stdin`?

Your Answer: With `./hello < in.txt
```
(Suggestion: With `./hello ? in.txt` -- replace the question mark)

```


**Q4.** If you remove the parentheses from the macro `ONE_PLUS_TWICE` in
`hello.c` the output line `Macro example: 26` changes. Why are the parentheses
important?

Your Answer: Important because it will complete muliplication instead of exponentiation
```
(Suggestion: Think about the precedence of operators `+` and `*`.)

```


**Q5.** You can compile with `gcc hello.c list.c -o hello`. Why don't you
need to pass `list.h` to GCC?

Your Answer: The compiler automatically includes the header file. .h files are supposed to included into the implementation files. GCC creates a precompiled header file for list.h
```
(Suggestion: Think about how #include works in .c files.)

```

**Q6.** Should `make` recompile `hello` when `list.h` changes? How is this
ensured in the `Makefile`?

Your Answer: yes the word "make" recompiles hello everytime some .h or .c file changes as the default goal in the makefile. 
Ensured->  CFLAGS += -Wall -Wextra -std=c11 -D_POSIX_C_SOURCE=200809L -MMD -MP -g
The -MMD and -MP create .d files to know which .h files are included in each .c file.
The -MMD creates the .d file that holds the rules in making the generated file depend on the source file and any headers it includes. The object file get regenerated automatically whenever relevant files are touched/modified. 
The -MP adds rules that avoid errors should header files be removed from the file system.
