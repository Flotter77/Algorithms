# Seamcarving Algorithm 

This algorithm uses Dijkstra's alogrithm to find the vertical or horizontal seam in a picture and removes it.

To Find the vertical seam:
  1. Created two 1D arrays to store distTo and edgeTo information for
  Dijkstra's algorithm, a 2D array to store energy for each pixel, and a
  priority queue to store and efficiently find the shortest distance
  in the queue. 
  2.Then inserted the first row of the energy into the queue, 
  which could be thought as edge weights connected to a virtual top vertex.
  3. Apply Dijkstra's algorithm to find the shortest distance:
          a. Get the lowest distTo[] value from prioirty queue
          b. Add vertex to tree and relax all edges adjacent from that vertex,
             which are three or two neighboring vertices in the next row.
          c. Run the algorithm till we reach the last row or there's no element in the queue.
          d. Found the last element of the seam from the queue, and then trace back 
             previous vertices on the path using edgeTo[] array. Then return the seam

  To find the horizontal seam:
  1. Transpose the picture
  2. Run findVerticalSeam() to find the horizontal seam
  3. Transpose the picture back

