import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph{
    int[][] graph;
    int size;
    public GraphImplementation(int vertices)
    {
        graph = new int[vertices][vertices];
        size = vertices;
    }

    public void addEdge(int src, int tar) throws Exception
    {
        if (src == tar)
            throw new Exception();
        graph[tar][src] = 1;
    }

    public List<Integer> topologicalSort()
    {
        List<Integer> result = new LinkedList<Integer>();
        int[] sum = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                sum[i] += graph[i][j];
        }

        for (int count = 0; count < size; count++)
        {
            int next = findZero(sum);
            sum[next] = -1;
            result.add(next);
            for (int i = 0; i < size; i++)
                sum[i] -= graph[i][next];
        }
        return result;
    }

    public int findZero(int[] v)
    {
        for (int i = 0; i < size; i++)
        {
            if(v[i] == 0)
                return i;
        }
        return -1;
    }

    public List<Integer> neighbors(int vertex) throws Exception
    {
        if(vertex < 0 && vertex >= size)
            throw new Exception();
        List<Integer> result = new LinkedList<Integer>();
        for (int i = 0; i < size; i++)
        {
            if(graph[i][vertex] == 1)
                result.add(i);
        }
        return result;
    }
}
