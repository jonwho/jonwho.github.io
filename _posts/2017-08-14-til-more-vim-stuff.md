---
layout: post
title: TIL more vim stuff
date: 2017-08-14 16:53:31
---

## Marks and Jumps
I've been using marks for quite some time now and just learned about file jumps
today. Marks make it super easy to move from different row and column positions
in your file, simply use a single character to mark a location in your file you
will want to move back to. Let's use `x` so if we mark say line 90, column 20
with `mx` we can then use `'x` to quickly move back to line 90 and if we want to
move to the specific character we were on we can use
```
`x
``` to move the cursor
onto line 90, column 20.

Now to something even better. I've always moved across many files to edit a few
lines in a different file to test some small changes. Sometimes I'll open the
file in a new tab sometimes I'll do it quick and open the file in place of my
current file. Now if I want to open that file again I might open up my existing
buffers or use NERDTree if it's open or just use CtrlP to find it again. But
replacing the current file actually counts as a vim jump. A jump is an action
like searching, substituting, and marks. In a jump list vim will record the file
column number and row number. To move back and forth between jumps in the jump
list you can you `<Ctrl-o>` (previous) and `<Ctrl-i>` (next). This is pretty
handy!

## Opening stuff under the cursor
Moving your cursor over words gives you a number of options with normal commands
like `*` which will search the word under the cursor for other matches in the
file. What if the word under your cursor is a file path or a url? You can easily
open them while still in vim! Use `gf` to open the file path in your vim session
or use `gx` to open it with the default program respective to its filetype. Or
use `gx` to open the url in your browser. Before I would copy and paste the url
what a waste of time!
