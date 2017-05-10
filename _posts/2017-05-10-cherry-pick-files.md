---
layout: post
title: Cherry pick individual files
date: 2017-05-10 15:49:31
---

Happened upon this old [stackoverflow post](http://stackoverflow.com/questions/5717026/how-to-git-cherry-pick-only-changes-to-certain-files)
when I needed changes only of a specific files and directories instead of a whole commit
from another one of my branches. In short you can run:

```
git show <YOUR_COMMIT_SHA> -- file1 file2 dir1/ | git apply -
```

which will bring in those files and directory changes to your current branch
without commit. You can then use those changes as you please and then commit.

This was a real time saver for me when I would probably just copypasta by hand.
