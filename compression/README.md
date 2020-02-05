# Burrows-Wheeler data compression algorithm

Move-to-front encoding:
The main idea of move-to-front encoding is to maintain an ordered sequence of legal symbols, 
and repeatedly read in symbols from the input message, print out the position in which that symbol appears, 
and move that symbol to the front of the list.

Burrows-Wheeler transform:
Burrows-Wheeler transform rearranges the characters in the input so that 
there are lots of clusters with repeated characters, but in such a way that it is still possible to recover the original input.
