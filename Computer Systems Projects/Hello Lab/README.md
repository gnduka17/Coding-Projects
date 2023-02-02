# USC CS356 Fall 2020: Hello Lab

## Introduction

This lab is mandatory but not graded.
The goal is to become familiar with our development environment:

- The class VM (running Debian Linux)
- The Linux shell (`bash`)
- The version control system (`git`)
- The editor (Visual Studio Code)
- The compiler (`gcc`)
- The build system (`make`)
- The debugger (`gdb` and the built-in debugger of VS Code)
- The main features of the C language

Understand each step of this guide.
It's a bit long but it explains very important things!


## Hand In

For this assignment, you need to write your answers inside `QUIZ.md`,
commit and push it by the assignment deadline (the push time matters,
not the date saved inside the commit message).


## The Class VM: Getting Started

To get started:

1. Install VirtualBox on your laptop.
   - Windows: download and install "platform packages" and "extension pack"
     from [here](https://www.virtualbox.org/wiki/Downloads)
   - macOS: `brew cask install virtualbox virtualbox-extension-pack`
     ([install brew](https://brew.sh/))
   - Linux: `apt-get install virtualbox virtualbox-guest-additions-iso`
2. Download the [class VM](http://qedwork.usc.edu/CS356.ova).
3. Import the class VM in VirtualBox (`File`, `Import Appliance`).
4. Start the VM and log in as `trojan`:`developer` (`user`:`password`).


## The Linux shell: Running Commands

Running commands on the shell is very important for this class.
Make sure that you can comfortably:

- Run a program in the current directory (`./hello`)
- List files and use wildcards (`ls -lh *.c`)
- Copy, rename/move, delete files (`cp`, `mv`, `rm`)
- Create, enter, copy, rename/move, delete directories
  `mkdir`, `cd`, `cp -r`, `mv`, `rm -rf` -- be careful!)
- Check the contents of files (`cat hello.c` or `less hello.c`)
- Search within files with `grep` (`grep main *.c`)
- Redirect `stdout` to file (`echo Tommy > name.txt`)
- Redirect `stdout` and `stderr` to file (`find /var/tmp -name "*" -print > out.txt 2>&1`)
- Append `stdout` to file (`ls >> list.txt`)
- Use a file as `stdin` (`./hello < name.txt`)
- Use a file as `stdin` and save `stdout` to another file (`./hello < name.txt > out.txt`)
- Pipe `stdout` into `stdin` (`cat name.txt | ./hello`)


## Git: clone, status, add, commit, push, pull

Git is the most popular version control system and we'll use GitHub to release
assignments and to receive your submissions.

It is essential that you understand:

- How to configure git on your VM:
  ```
  git config --global user.name "Your name"
  git config --global user.email your_email@usc.edu
  ```
- How to clone a repository: `git clone https://github.com/usc-cs356-fall20/repo_name.git`
- The difference between the history (graph of previous commits), the index
  (draft of next commit), the working directory (files currently in the directory).
- How to check which files have changed (`git status`)
- How to add file changes to the index (`git add .`)
- How to commit changes that you added to the index (`git commit -m "Add assignment files"`)
- How to push local commits (`git push`) and pull remote ones (`git pull`)


## C Programming: The Very Basics

We've prepared a simple "hello world" example highlighting many features of C:

- `hello.c` has a `main()` function, the entry point of each program.
- It receives arguments from the command line and prints them.
- It uses `static` functions (similar to `private` in C++ or Java).
- It uses functions from another file (`list.c`) by including its header `list.h`.
- It reads entire lines from the input stream `stdin`.
- It prints to `stdout` and `stderr`.
- It shows features of C99, like variable definitions in `for` loops or
  single-line comments with `//`.
- Inside `list.c` you can see how `malloc` and `free` are used.


## GCC: Compiling and Linking

The given program has three files: `hello.c`, `list.c` and `list.h`.
You can compile them by hand in one step with:

```
gcc -Wall -Wextra -std=c11 -D_POSIX_C_SOURCE=200809L hello.c list.c -o hello
```

To recompile only files that changed, a common approach is to compile
each `.c` file into a `.o` file and then link them together at the end, like this:

```
gcc -Wall -Wextra -std=c11 -D_POSIX_C_SOURCE=200809L -c hello.c -o hello.o
gcc -Wall -Wextra -std=c11 -D_POSIX_C_SOURCE=200809L -c list.c  -o list.o
gcc -Wall -Wextra -std=c11 -D_POSIX_C_SOURCE=200809L hello.o list.o -o hello
```


## Make: Recompiling dependecies after changes

Writing a `Makefile` and using `make` is a very common thing. The main goal is
to find automatically which `.c` files changed and which additional ones need to
be recompiled. Study the heavily-commented `Makefile` that we provided and try
to run `make clean`, `make` and then `./hello`.


## Visual Studio Code: Essential Tips

A very nice guide is available [here](https://code.visualstudio.com/docs/introvideos/basics).
If you're in a hurry:

- The VM has VS Code already installed with all of the extensions that you need.
- To start VS Code: `cd hellolab` and then `code .`
- Now, the assignment files are in the "Explorer" panel on the left.
- To open `hello.c`, click on it or: `CTRL+p`, type the name, press `ENTER`.
- You can save a file with `CTRL+s`.
- You can close a file with `CTRL+w`.
- Cut/copy/paste are the usual `CTRL+x`, `CTRL+c`, `CTRL+v`
- Undo/redo are `CTRL+z` and `CTRL+y` (or `CTRL+SHIFT+z`)
- With `CTRL+space` you can get a list of suggested completions.
- You can move to the next opened file with `CTRL+TAB`
- With `F12` you can go to a function/variable definition.
- With `CTRL+t` you can find a function and go to its definition.
- With `CTRL+ALT+-` you can go back.
- With `CTRL+f` you can find strings in a file.
- With `F9` you can toggle a breakpoint.
- With `F5` you can start/continue debugging.
- With `F10` you can step over during debugging.
- With `F11` you can step into during debugging.

Try to open `hello.c`, and:
- Compile `hello` with `CTRL+SHIFT+b` (this starts `make`).
- Set a breakpoint somewhere (F9), run the debugger (F5) and step over a few
  times (F10).


## Using GDB

In the "BombLab" and "AttackLab", we will use `gdb` to sift through the assembly
code of compiled programs. Try it now!

- `gdb hello`: opens the binary file and presents you with the command prompt.
- `layout split`: shows C source and assembly instructions together (the C
  source is available because the program was compiled with the `-g` flag).
- `b 38`: sets a breakpoint at line 38, the first statement of `main()`.
- `r`: runs the program, which will pause at line 38.
- `p argc`: prints the `argc` C variable.
- `p $rdi`: prints the `%rdi` register inside the CPU (holding `argc`).
- `c`: continues the execution of the program.
- `q`: quits GDB.
