# Game of Life <br />
[![DOI](https://zenodo.org/badge/289585062.svg)](https://zenodo.org/badge/latestdoi/289585062)<br />
[![Build Status](https://travis-ci.org/jayeshjakkani/seng20_21_HW2.svg?branch=master)](https://travis-ci.org/jayeshjakkani/seng20_21_HW2)<br />


The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves. It is Turing complete and can simulate a universal constructor or any other Turing machine.

Here, each generation a cell C is alive or dead. So, in the next generation, each cell is dead or alive depending on count on neighbours N.


| Now | Neighbours      | Next                              |
|-----|-----------------|-----------------------------------|
| 1   | 0,1             | 0 -> Lonely                       |
| 1   | 4,5,6,7,8       | 0 -> Overcrowded                  |
| 1   | 2,3             | 1 -> Lives                        |
| 0   | 3               | 1 -> It takes three to give birth |
| 0   | 0,1,2,4,5,6,7,8 | 0 -> Barren                       |


# Steps to execute the Code<br />
1) Open the Online Code Editor<br />
   Rust  : https://play.rust-lang.org/ <br />
   Scala : https://scastie.scala-lang.org/  <br />
   Go    : https://play.golang.org/  <br />
2) Copy paste the Code in the editor.<br />
3) Run the code to get the matrix of next generation and compare it with the expected output present in the comments.<br />





# MEMBERS<br />
Ashish Rajpurohit<br />
Jayesh Jakkani<br />
Katta Rishabh<br />
Keertikumar Malagund<br />
Sathwik Kalvakuntla<br />

