package com.example.data.structure.graph;

import java.util.LinkedList;

/**
 * Created by Evain on 2019/1/23.
 * 无向图
 */
public class Graph {
    private int v;//顶点个数
    private LinkedList<Integer> adj[];//零接表

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }

}

