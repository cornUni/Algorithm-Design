# A Python3 program to find single source
# longest distances in a DAG
import sys

def addEdge(u, v, w):
	
	global adj
	adj[u].append([v, w])

# A recursive function used by longestPath.
# See below link for details.
# https:#www.geeksforgeeks.org/topological-sorting/
def longestPathUtil(v):
	
	global visited, adj,Stack
	visited[v] = 1

	# Recur for all the vertices adjacent
	# to this vertex
	for node in adj[v]:
		if (not visited[node[0]]):
			longestPathUtil(node[0])

	# Push current vertex to stack which
	# stores topological sort
	Stack.append(v)

# The function do Topological Sort and finds
# longest distances from given source vertex
def longestPath(s):
	
	# Initialize distances to all vertices
	# as infinite and
	global visited, Stack, adj,V
	dist = [sys.maxsize for i in range(V)]
	# for (i = 0 i < V i++)
	#	 dist[i] = INT_MAX
	dist[s] = 0

	for i in range(V):
		if (visited[i] == 0):
			longestPathUtil(i)

	# print(Stack)
	while (len(Stack) > 0):
		
		# Get the next vertex from topological order
		u = Stack[-1]
		del Stack[-1]

		if (dist[u] != sys.maxsize):
			
			# Update distances of all adjacent vertices
			# (edge from u -> v exists)
			for v in adj[u]:
				
				# Consider negative weight of edges and
				# find shortest path
				if (dist[v[0]] > dist[u] + v[1] * -1):
					dist[v[0]] = dist[u] + v[1] * -1

	# Print the calculated longest distances
	for i in range(V):
		if (dist[i] == sys.maxsize):
			print("INT_MIN ", end = " ")
		else:
			print(dist[i] * (-1), end = " ")

# Driver code
if __name__ == '__main__':
	
	V = 7
	visited = [0 for i in range(7)]
	Stack = []
	adj = [[] for i in range(10)]

	addEdge(0, 6, 7)
	addEdge(0, 1, 2)
	addEdge(1, 2, 3)
	addEdge(1, 3, 3)
	addEdge(6, 3, 3)
	addEdge(3, 5, 1)
	addEdge(6, 5, 1)
	addEdge(2, 5, 1)
	addEdge(0, 4, 5)
	addEdge(4, 6, 2)

	s = 1

	print("Following are longest distances from source vertex", s)
	
	longestPath(s)

# This code is contributed by mohit kumar 29
