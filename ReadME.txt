The entire code is written in java.

In total there are 6 java classes for this project

1. Main (Main class of this project)
2. Edge (Edge class for adding Edges)
3. Graph (Graph class is used to create adjacency lists - graph from .txt files)
4. BreadthFirstSearch (This class has bfs implementation and gives shortest path between source and sink)
5. FordFulkerson (This class gives the max flow in the graph by using BreadthFirstSearch class)
6. GraphGeneration (This class is used to generate random test cases).

To execute Network-Flow-and-Circulation-with-Demands-Problem: 

* The source code and test cases should be in the same folder.
* There should be a blank line for sink. If sink is the last node, there should be a blank line after sink node line. (This is to consider end of the file).
* Input for BFS and FFA algorithm should be toNodes and edge weights. There can also be blank lines in between.
* Input for Circulation problem should be supply/demand then followed by toNodes and edge weight.

To run the program: 

First run javac Main.java

Then to run the bfs algorithm

java Main –b *.txt source sink // testing for Breadth-First Search implementation; -b for bfs, * should be replaced with .txt file name, source value = 0, sink value = nodes -1

java Main –f *.txt // testing your Ford-Fulkerson implementation; -f for ford-fulkerson

java Main –c *.txt//testing your application to the circulation problem; -c for circulation