package com.example.mysampleapp.algorithm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DFS {

    private int n;
    private int cnt = 0;

    private int[][] a = new int[5][5];

    @Test
    public void testDFS() throws Exception {
        n = 5;

        int[] data = new int[]
                {
                        1, 1, 1, 0, 0,
                        0, 0, 1, 1, 1,
                        1, 1, 1, 0, 1,
                        1, 0, 0, 0, 1,
                        1, 1, 1, 1, 1
                };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = data[i * n + j];
            }
        }

        depthFirstSearch(0, 0, 0);

        System.out.println(cnt);
    }

    private void depthFirstSearch(int y, int x, int count) {
        // 마지막 위치에 도달하면 경로 개수 1 증가, 종료 조건
        if (y ==  n - 1 && x == n - 1) {
            cnt++;
            System.out.println("count: " + (++count));
            return;
        }

        System.out.println("DFS in x: " + x + "\ty: " + y);
        count++;

        // 다시 방문하지 않도록 0으로 임시적으로 바꿈
        a[y][x] = 0;

        // 위쪽으로 방문
        if (y > 0 && a[y-1][x] == 1) depthFirstSearch(y-1, x, count);

        // 아래쪽으로 방문
        if (y < n-1 && a[y+1][x] == 1) depthFirstSearch(y+1, x, count);

        // 왼쪽으로 방문
        if (x > 0 && a[y][x-1] == 1) depthFirstSearch(y, x-1, count);

        // 오른쪽으로 방문
        if (x < n-1 && a[y][x+1] == 1) depthFirstSearch(y, x+1, count);

        // 1로 다시 복원
        a[y][x] = 1;
    }
}