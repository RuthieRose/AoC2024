# <a name="top">Advent of Code 2024!! 🌟 🌟</a>

[Prelude](#prelude)  
[Day 1](#day-1)  
[Day 2](#day-2)  
[Day 3](#day-3)  
[Day 4](#day-4)  
[Day 5](#day-5)  
[Day 6](#day-6)  
[Day 7](#day-7)  
[Day 8](#day-8)  
[Day 9](#day-9)  
[Day 10](#day-10)  
[Day 11](#day-11)  
[Day 12](#day-12)  
[Day 13](#day-13)  
[Day 14](#day-14)  
[Day 15](#day-15)  
[Day 16](#day-16)  
[Day 17](#day-17)  
[Day 18](#day-18)  
[Day 19](#day-19)  
[Day 20](#day-20)  
[Day 21](#day-21)  
[Day 22](#day-22)  
[Day 23](#day-23)  
[Day 24](#day-24)  
[Day 25](#day-25)  

## <a name="Prelude">Prelude</a>

### Goals for this year

I'd like to finish all 25 days. That's a tall ask considering it would be my first time to finish all 25 days, but that's the goal.

### Timing

I do have a job that pays the bills, so that comes first. The coding will happen around that priority. Otherwise, my kids are prepared and know the memes are coming. Hubby is supportive and well aware that I will be under a rock coding on the hard days. My dog is also ready to cuddle in my lap while I work and go for walks when I need to think!

### Approach

~~I will be using JavaScript again this year, not a year where I have bandwidth to learn a new language. I am primarily using TypeScript and Java at work, so this will help me stay sharp for the TypeScript portion. :)~~

Found out I will be working in all Java/SpringBoot for 2025, so there has not been a better time to get going!! 

This year when I get truly stuck I will seek out help, but my code will be my own implementation. 

### Preparation

I'm lucky enough to have an O'Reilly media subscription and I am working my way through the Algorithms course by Robert Sedgewick & Kevin Wayne, relearning everything I've forgotten & not used in my day-to-day. :) 

I'm also doing all setup before, so I can just jump in and read and code... we'll see how that goes. :) 


~~Update... found out how to read in a line at a time with nodejs :) thanks to Stack Overflow (of course) (https://stackoverflow.com/questions/6156501/read-a-file-one-line-at-a-time-in-node-js)~~

Update, java edition: 
file read in courtesy stack overflow: 
https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java

[back to top](#top)

## <a name="day1">Day 1</a>

Day 1 was pretty straightforward (as expected). My family had a really great day yesterday, so I was already really happy and excited. Woke up about 3 a.m. and knew I would not get back to sleep, so I made some coffee and got to work. I would have had this knocked out in minutes in Python, but I'm hoping to learn more Java so spent some time getting reacquainted with Java methods and its strict typing. Part 1 was a very simple parse, sort & diff. For part 2 the items needed to be stored in a map by frequency of set 2, and then that frequency retrieved for each of set 1. My biggest issue was being slow with Java methods, typing and data structure. Maybe that will get faster as I go! 🙃 After completing the challenge I went back and read up on why I had warnings and fixed those--have to stay type safe!! 

[back to top](#top)

## <a name="day2">Day 2</a>

Day 2 part 1 was not pretty fair with a few if conditions. Part 2 added a little complexity with needing to test each set with one member removed. While I decided to brute force this, I added a filter to check for too many direction changes in order to cut down on the number of tries needed. 

Overall, this is taking me longer than it normally would because of Java's strict typing limitations, and I'm also not super familiar with all of the Java collection methods or any libraries. It is nice because I'm debugging before I get the answer rather than after--I do have to give credit there. It will be interesting to see how much I can pick up in future days or if I will be switching back to Python. :D

[back to top](#top)

## <a name="day3">Day 3</a>

Part 1 was fine. Just how to get regex in Java. Solved that in minutes.

;ajdsf;kafdjkla;dkj;f
Part 2. 
The input. !!!!!
I lost two hours trying to figure out if there was something wrong with my regex or my data structures. I broke it down into smaller pieces (that's why I have 3c). As it turns out, the new lines in the puzzle input themselves were messing with the regex pattern. As soon as I cleaned up the input to be one line, I had the right answer. 

[back to top](#top)

## <a name="day4">Day 4</a>

Today's puzzle was more about understanding the logic than anything else. I'm still super slow coding in Java but I really took the time to make notes about how to organize the logic. I got the answer correct first time for both parts. If I had more time I perhaps could have optimized the data structure better for Part 2 but today was not about optimization. It was about being careful and detail-oriented with the functions and I'm super happy I didn't make any stupid mistakes. I'm also happy that the puzzle didn't wrap, but that may be saved for another day :D 

[back to top](#top)

## <a name="day5">Day 5</a>

This was an interesting problem to solve. For part 1 I opted for a data structure that would use the higher page number as the key and the lower page numbers would be the values in an array. I iterated through each set of pages, checking to see if each page had a required page before it that was after it in sequence. If it did, then that set would not count toward the total.

Part 2 I had to really think about how to sort and hope that it was not a complex sort. I did a simple swap and the trick was to backtrack in the array so as to keep any changes but also institute any additional changes that needed to be made.

[back to top](#top)

## <a name="day6">Day 6</a>

Today's focus was breaking this down into small enough parts to code an implementation. Part 1 was simple to understand and not so simple to execute. I would have wanted to put anything repeated in a function, but getting to work on time is also important. :) 

Part 2 I just kind of stared at for a few minutes. I love that he does examples but I couldn't see any visual rhyme or reason that I could then code to adding in obstacles. I zoomed out a little and thought if I were to tackle it programmatically using a simple (ie brute force) solution, I would just check every point to see if it created a loop. I defined a loop as any move count greater than twice the area of the coordinate grid. The part I was unsure of was refactoring my code to make part 1 a separate function so I could iterate through the grid. 

I had one small hiccup where my move count was outside my while loop. Once I refactored that I was able to get the solution quickly. I'm sure there's a great algorithm for this, and maybe I will have time to learn it this weekend. :) We'll see how hard the next challenges are. 

[back to top](#top)
## <a name="day7">Day 7</a>

Today's puzzle was super cool, probably my favorite of them all so far this year. For part 1 in getting all of the combinations for multiplying and adding, I knew exactly what I needed to do, but wasn't sure how to code it. I did a google search and the AI snippet produced the use of bitwise operators. I've played around a little with learning them, so decided to try it out, of course modifying the code that was given to work with my situation. Later today, after completing part 2 and taking care of errands and some holiday things, I really wanted to dig into understanding why it worked so well. I input a code snippet into ChatGPT and found it educational. I included our conversation in the Day 7 folder (https://github.com/RuthieRose/AoC2024/tree/main/Day7/ChatGPTconvo.txt). It is pretty cool and I want to practice more with bitwise operations. 

With the addition of a third operator, I opted for refactoring to a recursive call to generate the sequences. This was also inspired by a google search response. Usually Stack Overflow is my go-to, but this was readable so I opted to adapt it for my purposes. 

I've been laughing at all of the Advent of Code memes on Reddit but one thing I'm not getting is some of these taking a long time to run. I know I'm brute forcing this--the last run of part 2 took 2703 milliseconds. Is Java just *that* fast? If so... between the type safety saving me a lot of time debugging and the speed... this is quickly becoming my preferred language... I'm too ADHD for Python I think....

[back to top](#top)

## <a name="day8">Day 8</a>

Whew, this was fairly straightforward but getting the math right was a tricky. I got a little help on the exact math, but the real catch on part 2 was getting those extra anti-nodes accounted for and added. :) 

[back to top](#top)
## <a name="day9">Day 9</a>

I enjoy a good string parsing that requires me to flip around indices and values lol. I misread the instructions for the second part and lost some time deriving logic as a result (you can see though the git commits how this changed). I finally got it resolved and submitted. :) 

[back to top](#top)

## <a name="day10">Day 10</a>

Have to admit I'm prioritizing a little more sleep this week! Part 2 is getting solved during lunches so far. :) Today was a typical DFS problem, although it's been a while since I've done DFS so I had to review. I spent more time trying to understand the difference between part 1 and part 2 until it occurred to me that removing the visited would likely be the fix. :) Love it when code is that easy to modify. 

[back to top](#top)

## <a name="day11">Day 11</a>

Part 1 was too easy, that meant part 2 would give an out of memory error. :D I did get a hint today because I completely forgot that the frequency of each number can be stored in a hashmap and retrieved to make the next set. Once I implemented that and a hashmap for the values themselves, my big bug was that the sample was working, part 1 was working, and part 2 was too low. I was able to see negative values for numbers which told me I had to change all my ints to longs for values and that fixed it. 

At this point I've now done better than last year so now I'm chasing down 2022!! Let's go!!

[back to top](#top)

## <a name="day12">Day 12</a>

I just really thought I had great ideas today and then spent every spare moment debugging. I even thought of the corners == edges without seeing any hints!! I just can't code apparently. :D :D :D My part 2 works on ALL the smaple inputs and not the real input!! Oh well...

[back to top](#top)

## <a name="day13">Day 13</a>

This one was math!! This tutorial at Reddit was a great reminder of what I had forgotten about systems of equations: [https://www.reddit.com/r/adventofcode/comments/1hd7irq/2024_day_13_an_explanation_of_the_mathematics/]

Part 2 was kinda silly & kinda fun... BIG DECIMAL for real this time lol. 

[back to top](#top)

## <a name="day14">Day 14</a>

I figured part 1 out super fast. For part 2 I knew I didn't know anywhere near enough to arrive at this so I used this explanation to help and sure enough, when I made the input arrays high enough, I got the correct answer. 

Since I didn't look for the tree by visualizing it at each iteration I made another file to create the tree! [https://github.com/RuthieRose/AoC2024/tree/main/Day14/MerryChristmas.txt]
[back to top](#top)
## <a name="day15">Day 15</a>
[back to top](#top)
## <a name="day16">Day 16</a>
[back to top](#top)
## <a name="day17">Day 17</a>
[back to top](#top)
## <a name="day18">Day 18</a>
[back to top](#top)
## <a name="day19">Day 19</a>
[back to top](#top)
## <a name="day20">Day 20</a>
[back to top](#top)
## <a name="day21">Day 21</a>
[back to top](#top)
## <a name="day22">Day 22</a>
[back to top](#top)
## <a name="day23">Day 23</a>
[back to top](#top)
## <a name="day24">Day 24</a>
[back to top](#top)
## <a name="day25">Day 25</a>
[back to top](#top)