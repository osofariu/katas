Conway's Game of Life with one restriction: there should be no conditional statements in the code.

This added some uglyness, but it did end up helping in one respect: by adding dead neighbors every time a connection is made - to avoid checking for null - we created cells that could become alive in the next iteration, so when I check for (dead && three neighbors) the cells are already there; no special case had to be made for bringing cells back to life.

