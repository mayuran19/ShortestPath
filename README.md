Steps to run
============
**JDK 8 or higher is required**
1. Import the project using any IDE as gradle project
2. Execute the main method
3. To test with different inputs, change the file input.txt in resources folder
4. Input should be in the following format <A,B,5>
5. If the input is not valid, application ignores the input and builds the graph without that edge

Algorithm
=========
To find the path between two nodes,
1. Visit the start node(Processing starts there)
2. Get all outgoing edges
3. Follow each outgoing edge and move the adjacent node. 
4. Add the node to a stack
5. If the adjacent node is the destination, add the elements in stack to a path
6. Continue visiting until all edges are visited(Tracked using a variable in edge and edge in stack)