import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class Algo {
    public static int combNum = 0;
    public static int permNum = 0;

    public void combination(int[] nums, List<List<Integer>> res, List<Integer> temp, int index, int m) {
        if (m == 0) {
            combNum++;
            res.add(new ArrayList<Integer>(temp));
            System.out.println(temp);
            return;
        }

        for(int i = index; i < nums.length; i++) {
            System.out.println("index = " + index + "  i = " + i);
            if (i != index && nums[i] == nums[i - 1]) {
                System.out.println("continue");
                continue;
            }
            temp.add(nums[i]);
            combination(nums, res, temp, i + 1, m - 1);
            temp.remove(temp.size() - 1);
        }
    }

    public void permutation(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used, int m) {
        if (m == 0) {
            permNum++;
            res.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i != 0 && nums[i] == nums[i-1]) continue;
                temp.add(nums[i]);
                used[i] = true;
                permutation(nums, res, temp, used, m-1);
                used[i] = false;
                temp.remove(temp.size()-1);
            }
        }
    }

    public String[] findWords(String[] words) {
        String[] keyBorad = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < keyBorad.length; i++) {
            for(char c : keyBorad[i].toCharArray()) {
                map.put(c, i);
            }
        }

        for (String word : words) {
            if (word.equals("")) continue;
            char[] curWord = word.toLowerCase().toCharArray();
            int index = map.get(curWord[0]);
            for (char c : curWord) {
                if (index != map.get(c)) {
                    index = -1;
                    break;
                }
            }
            if (index != -1) {
                res.add(word);
            }
        }

        String[] ret = new String[res.size()];
        for (int i = 0; i < ret.length; i++){
            ret[i] = res.get(i);
        }
        return ret;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int[] findMode(TreeNode root) {
        if (root == null) return null;
        TreeNode pre = root;
        List<Integer> res = new ArrayList<Integer>();
        int[] maxArr = new int[2];
        findMode(root, pre, res, maxArr);
        int[] ret = res.stream().mapToInt(i->i).toArray();
        return ret;
    }

    private void findMode(TreeNode root, TreeNode pre, List<Integer> res, int[] maxArr) {
        if (root == null) return;
        System.out.println("root value is " + root.val);
        System.out.println("maxArr array is: ");
        for (int i : maxArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("------------------------------");
        findMode(root.left, pre, res, maxArr);
        if (pre != null) {
            if (root.val == pre.val) {
                System.out.println("incrementing maxArr[1]");
                maxArr[1] += 1;
            } else {
                System.out.println("resetting maxArr[1]");
                maxArr[1] = 1;
            }

            if (maxArr[0] <= maxArr[1]) {
                if (maxArr[0] < maxArr[1]) res.clear();
                res.add(root.val);
                maxArr[0] = maxArr[1];
            }
            System.out.println("assigning pre to root");
        }

        pre = root;

        findMode(root.right, pre, res, maxArr);
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze.length == 0 || maze[0].length == 0) return false;
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        int row = maze.length;
        int col = maze[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        return hasPath(maze, start[0], start[1], dir, visited, destination);
    }

    private boolean hasPath(int[][] maze, int x, int y, int[][] dir, boolean[][] visited, int[] destination) {
        if (x == destination[0] &&  y == destination[1]) return true;
        int row = maze.length;
        int col = maze[0].length;
        if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y]) return false;
        visited[x][y] = true;
        for (int i = 0;  i < 4; i++) {
            int nx = x;
            int ny = y;
            while (nx >= 0 && nx < row && ny >= 0 && ny < col && maze[nx][ny] == 0) {
                nx += dir[i][0];
                ny += dir[i][1];
            }
            nx -= dir[i][0];
            ny -= dir[i][1];
            if (hasPath(maze, nx, ny, dir, visited, destination)) return true;
        }
        visited[x][y] = false;
        return false;
    }

    public int[] getNext(String ps) {
        char[] psArr = ps.toCharArray();
        int[] next = new int[ps.length()];
        int j = 0;
        int k = -1;
        next[0] = -1;

        while(j < psArr.length - 1) {
            if (k == -1 || psArr[j] == psArr[k]) {
                if (psArr[++j] == psArr[++k]) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<Point> queue = new LinkedList<Point>();
        Point src = new Point(start[0], start[1]);
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] distToSrc = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                distToSrc[i][j] = Integer.MAX_VALUE;
            }
        }
        queue.offer(src);
        distToSrc[src.x][src.y] = 0;

        while (!queue.isEmpty()) {
            Point curPnt = queue.poll();
            for (int[] nxtDir : dir) {
                int step = 0;
                int nx = curPnt.x;
                int ny = curPnt.y;

                while (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze[0].length
                        && maze[nx][ny] == 0) {
                    nx += nxtDir[0];
                    ny += nxtDir[1];
                    step++;
                }
                nx -= nxtDir[0];
                ny -= nxtDir[1];
                step--;
                if (distToSrc[curPnt.x][curPnt.y] + step < distToSrc[nx][ny]) {
                    distToSrc[nx][ny] = distToSrc[curPnt.x][curPnt.y] + step;
                }
                queue.add(new Point(nx, ny));
            }
        }

        return distToSrc[destination[0]][destination[1]] == Integer.MAX_VALUE ?
                -1 : distToSrc[destination[0]][destination[1]];
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length;
        int col = maze[0].length;
        PriorityQueue<Point> pq = new PriorityQueue<Point>();
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        String[] directions = {"u", "d", "r", "l"};
        boolean[][] visited = new boolean[row][col];

        pq.add(new Point(ball[0], ball[1], 0, ""));
        while(!pq.isEmpty()) {
            Point cur = pq.poll();
            if (cur.x == hole[0] && cur.y == hole[1]) return cur.path;

            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;

            for (int i = 0; i < dir.length; i++) {
                int nx = cur.x;
                int ny = cur.y;
                int ndis = cur.dis;

                while (nx + dir[i][0] >= 0 && nx + dir[i][0] < row
                        && ny + dir[i][1] >= 0 && ny + dir[i][1] < col
                        && maze[nx + dir[i][0]][ny + dir[i][1]] == 0
                        && (nx + dir[i][0] != hole[0] || ny + dir[i][1] != hole[1])) {
                    nx += dir[i][0];
                    ny += dir[i][1];
                    ndis ++;
                }

                Point nxt = new Point(nx, ny, ndis, cur.path + directions[i]);
                pq.offer(nxt);
            }
        }
        return "impossible";
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        return null;
    }


    public int[] nextGreaterElements(int[] nums) {
        return null;
    }

    public int nextGreaterElements(int num) {
        return 0;
    }

    public String convertToBase7(int num) {

        return null;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[] {};
        Map<Integer, Integer> freq = new HashMap<>();
        int[] maxFreq = new int[1];
        List<Integer> res = new ArrayList<>();
        postOrderFindSum(root, freq, maxFreq, res);
        return res.stream().mapToInt(num -> Integer.valueOf(num)).toArray();
    }

    public int findBottomLeftValue(TreeNode root) {
        return findLeftMostNode(root, 0, new int[] {-1, 0});

    }

    private int findLeftMostNode(TreeNode root, int depth, int[] values) {
        if (root == null) return 0;
        if (depth > values[0]) {
            values[0] = depth;
            values[1] = root.val;
        }

        findLeftMostNode(root.left, depth+1, values);
        findLeftMostNode(root.right, depth+1, values);
        return values[1];
    }

    private int postOrderFindSum(TreeNode root, Map<Integer, Integer> freq,
                                 int[] maxFrq, List<Integer> res) {
        if (root == null) return 0;
        int leftSum = postOrderFindSum(root.left, freq, maxFrq, res);
        int rightSum = postOrderFindSum(root.right, freq, maxFrq, res);
        int curSum = leftSum + rightSum + root.val;

        freq.put(curSum, freq.getOrDefault(curSum, 0)+1);
        if (maxFrq[1] <= freq.get(curSum)) {
            if (maxFrq[1] < freq.get(curSum)) {
                res.clear();
            }
            res.add(curSum);
        }
        maxFrq[1] = Math.max(maxFrq[1], freq.get(curSum));
        return curSum;
    }

    public List<Integer> largestValuesBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;

        while(!queue.isEmpty()) {
            size = queue.size();
            int largest = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                largest = Math.max(largest, cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(largest);
        }
        return res;
    }

    public List<Integer> largestValuesDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        getLargestVaules(root, res, 0);
        return res;
    }

    private void getLargestVaules(TreeNode root, List<Integer> res, int depth) {
        if (root == null) return;

        if (res.size() == depth) {
            res.add(root.val);
        } else {
            if (res.get(depth) < root.val) {
                res.set(depth, root.val);
            }
        }
        getLargestVaules(root.left, res, depth+1);
        getLargestVaules(root.right, res, depth+1);
    }

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }

    public int change(int amount, int[] coins) {
        if (coins.length == 1 && amount%coins[0] != 0) return 0;
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 0;
            for (int j = 0; j <= amount; j++) {
                int curOpt = (j >= coins[i-1]) ? dp[i][j-coins[i-1]] : 0;
                dp[i][j] = dp[i-1][j] + curOpt;
                System.out.println("curOpt is: " + curOpt);
                System.out.println("i == " + i + " j == " + j + " dp[i][j] == " + dp[i][j]);
            }
        }
        return dp[coins.length][amount];
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length == 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > 0 && k == 0) return false;
            int reminder = (k == 0) ? sum : sum%k;

            if (map.get(reminder) != null && i - map.get(reminder) > 1) return true;
            else {
                map.put(reminder, i);
            }
        }

        return false;
    }


    public int changeDfs(int amount, int[] coins) {
        if (coins.length == 1 && amount % coins[0] != 0) return 0;
        int[] res = new int[1];
        change(amount, coins, res, 0, new ArrayList<Integer>());
        return res[0];
    }

    private void change(int amount, int[] coins, int[] res, int start, List<Integer> temp) {
        if (amount == 0) {
            res[0]++;
            System.out.println("res == " + res[0]);
            return;
        }
        if (amount < 0) return;

        for (int i = start;  i < coins.length; i++) {
            amount -= coins[i];
             temp.add(coins[i]);
            if (amount == 0) {
                System.out.println("amount == 0; i == " + i + "; coins[i] == " + coins[i]);
                System.out.println("temp list is: ");
                for (int element : temp) {
                    System.out.print(element + " ");
                }
                System.out.println(" ");
            }
            change(amount, coins, res, i, temp);
            temp.remove(temp.size()-1);
            amount += coins[i];
        }
    }

    /*
     Input:
     s = "abpcplea", d = ["ale","apple","monkey","plea"]

     Output:
     "apple"
     */
    public String findLongestWord(String s, List<String> d) {
        if (d == null || d.size() == 0) return "";
        String res = "";
        int ptr1 = 0;
        int ptr2 = 0;

        for (String word : d) {
            while (ptr1 < s.length()) {
                if (s.charAt(ptr1) == word.charAt(ptr2)) {
                    ptr2++;
                }
                ptr1++;
                if (ptr2 == word.length()) {
                    if (word.length() > res.length() ||
                            (word.length() == res.length() && word.compareTo(res) < 0)) {
                        res = word;
                    }
                    break;
                }
            }

            ptr1 = 0;
            ptr2 = 0;
        }
        return res;
    }


    /*

    [0, 0, 1, 1, 1]
     */
    public int findMaxLength(int[] nums) {

        return 0;
    }


    public int countArrangement(int N) {
        if (N == 1) return 1;
        boolean[] visited = new boolean[N+1];
        int[] res = new int[1];
        countArrangement(N, 1, visited, res);
        return res[0];

    }

    private void countArrangement(int n, int pos, boolean[] visited, int[] res) {
        if (pos > n) {
            res[0]++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && (i%pos == 0 || pos%i == 0)) {
                visited[i] = true;
                countArrangement(n, pos+1, visited, res);
                visited[i] = false;
            }
        }
    }

    /*
    Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
    Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
     */
    public List<String> wordsAbbreviation(List<String> dict) {
        if (dict == null || dict.size() == 0) return new ArrayList<String>();
        String[] abbr = new String[dict.size()];
        int[] len = new int[dict.size()];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < dict.size(); i++) {
            len[i] = 1;
            abbr[i] = getAbbr(dict.get(i), len[i]);
        }

        for (int j = 0; j < abbr.length; j++) {
            while (true) {
                for (int k = j+1; k < abbr.length; k++) {
                    if (abbr[j].equals(abbr[k])) {
                        set.add(k);
                    }
                }
                if (set.isEmpty()) break;
                set.add(j);
                for (int idx : set) {
                    abbr[idx] = getAbbr(dict.get(idx), ++len[idx]);
                }
                set.clear();
            }
        }
        return Arrays.asList(abbr);
    }

    // @param: length
    // length that taken from the original string, not including the first and last letter
    private String getAbbr(String word, int length) {
        if (length >= word.length() - 2) {
            return word;
        }

        return word.substring(0, length) + (word.length() - 1 - length) + word.charAt(word.length()-1);
    }

    /*

     */
    public String isReachable(int[] arr, int target) {

        return null;
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        return null;

    }

    /*
    Input: ["4", "13", "5", "/", "+"]
    Output: 6
    Explanation: (4 + (13 / 5)) = 6
    */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        int op1 = 0;
        int op2 = 0;
        Stack<Integer> stack = new Stack<>();

        for (String str : tokens) {
            if (str.equals("/")) {
                op2 = stack.pop();
                op1 = stack.pop();
                int tmp = op1 / op2;
                stack.push(tmp);
            } else if (str.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (str.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (str.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();
    }

    /*
    Check一个字符串能不能由另外一个字符串中的字符组成
    followup: what if input string is too big to fit in set?
    distributed storage and comparision.
     */
    public boolean canBeformed(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return true;
        Set<Character> set = new HashSet<>();
        for (char c : s2.toCharArray()) {
            set.add(c);
        }
        for (char t : s1.toCharArray()) {
            if (!set.contains(t)) return false;
        }
        return true;
    }

    public TreeNode constructBinaryTreeWithPostAndPre(int[] pre, int[] post, int preIdx, int postL,
                                                      int postR, Map<Integer, Integer> idxMap) {
        if (rootIdx >= pre.length || postL > postR) return null;
        TreeNode root = new TreeNode(pre[rootIdx]);
        rootIdx++;
        System.out.println("new root is: " + root.val);
        if (rootIdx >= pre.length || postL == postR) return root;
        int idx = idxMap.get(pre[rootIdx]);
        System.out.println("root is: " + root.val);
        System.out.println("rootIdx is: " + (rootIdx-1) + "  postL is: " + postL + "  PostR is: " + postR
        + "  idx is: " + idx);

        root.left = constructBinaryTreeWithPostAndPre(pre, post, preIdx+1, postL, idx, idxMap);
        System.out.println("root.left is: " + (root.left == null? -1 : root.left.val));
        System.out.println("start calling root.right, and rootIdx is: " + (rootIdx-1));
        root.right = constructBinaryTreeWithPostAndPre(pre, post, idx+1, idx+1, postR-1 , idxMap);
        System.out.println("root.right is: " + (root.right == null ? -1 : root.right.val));
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return BSTbuilder(nums, 0, nums.length);
    }

    private TreeNode BSTbuilder(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (start == end) return root;
        root.left = BSTbuilder(nums, start, mid - 1);
        root.right = BSTbuilder(nums, mid+1, end);
        return root;
    }

    public TreeNode sortedArrayToBalancedBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        return balancedBSTbuilder(nums, 0, nums.length);
    }

    private TreeNode balancedBSTbuilder(int[] nums, int start, int end) {

        return null;
    }

    public static int rootIdx = 0;

    public int findMinInRotatedArray(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return arr[0];

        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo + 1 < hi) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[hi]) lo = mid;
            else if (arr[mid] < arr[hi]) hi = mid;
            else hi--;
        }
        return arr[hi] < arr[lo] ? arr[hi] : arr[lo];
    }

    public int findMinInRotatedArrayWithDuplication(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return arr[0];

        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo + 1 < hi) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[hi]) lo = mid;
            else hi = mid;
        }
        return arr[hi] < arr[lo] ? arr[hi] : arr[lo];
    }


    public boolean findTargetInRotatedArray(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        if (arr.length == 1 && arr[0] != target) return false;

        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo + 1 < hi) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid] < arr[lo]) {
                if (arr[mid] <= target && arr[hi] >= target) lo = mid;
                else hi = mid;
            } else {
                if (arr[mid] >= target && arr[lo] <= target) hi = mid;
                else lo = mid;
            }
        }
        System.out.println("lo = " + lo + "  hi = " + hi);
        if (arr[lo] == target || arr[hi] == target) return true;
        return false;
    }

    public boolean findTargetInRotatedArrayWithDuplication(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;
        if (arr.length == 1 && arr[0] != target) return false;

        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo + 1 < hi) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid] < arr[lo]) {
                if (arr[mid] <= target && arr[hi] >= target) lo = mid;
                else hi = mid;
            } else if (arr[mid] > arr[lo]) {
                if (arr[mid] >= target && arr[lo] <= target) hi = mid;
                else lo = mid;
            } else {
                lo++;
            }
        }
        System.out.println("lo = " + lo + "  hi = " + hi);
        if (arr[lo] == target || arr[hi] == target) return true;
        return false;
    }

    public int searchInsert(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        int lo = 0;
        int hi = arr.length - 1;
        int mid;

        while (lo + 1 < hi) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] > target) hi = mid;
            else lo = mid;
        }
        if (arr[hi] < target) return hi+1;
        else if (arr[lo] < target) return lo + 1;
        else return lo;
    }

    public int getOccurrence(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo + 1 < hi) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid] >= target) hi = mid;
            else lo = mid;
        }
        int loBnd = (arr[lo] == target) ? lo : hi;

        lo = 0;
        hi = arr.length - 1;
        while (lo + 1 < hi) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid] <= target) lo = mid;
            else hi = mid;
        }
        int hiBnd = (arr[hi] == target) ? hi : lo;
        if (arr[loBnd] != target && arr[hiBnd] != target) return 0;
        else if (arr[loBnd] == target && arr[hiBnd] == target) return hiBnd - loBnd + 1;
        else return 1;
    }

    public int minArea(char[][] image, int x, int y) {

        return -1;
    }

    List<Integer> generateTwoNList(int n) {
        if (n == 1) return null;
        int[] temp = new int[2*n];
        boolean[] used = new boolean[2*n + 1];
        List res = new ArrayList();
        dfs(n, temp, res, 0, used, 0);
        return res;
    }

    private void dfs(int n, int[] temp, List res, int start, boolean[] used, int marked) {
        System.out.println("start == " + start + " marked == " + marked + " n == " +n);
        if (marked == 2*n) {
            res = Arrays.asList(temp);
            System.out.println("got a hit!");
            for (int j = 0; j < temp.length; j++) {
                System.out.print(temp[j] + " ");
            }
            System.out.println();
            return;
        }

        if (start == 2*n) return;

        for (int i = 1; i <= n; i++) {
            System.out.println("inside loop!");
            if (!used[i] && temp[start] == 0 && start+1+i < 2*n) {
                temp[start] = i;
                temp[start+1+i] = i;
                used[i] = true;
                marked += 2;
                dfs(n, temp, res, start+1, used, marked);
                used[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        Algo inst = new Algo();
        List<Integer> res = inst.generateTwoNList(3);


        /*
        int[] arr = {6,6,6,6,6,6,6};
        System.out.println(inst.getOccurrence(arr, 6));

        int amount = 5;
        int[] coins = {1,2,5};

        int[] pre = { 1, 2, 4, 8, 9, 5, 3, 6, 7 };
        int[] post = { 8, 9, 4, 5, 2, 6, 7, 3, 1 };
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < post.length; j++) {
                if (pre[i] == post[j]) {
                    map.put(pre[i], j);
                }
            }
        }

        TreeNode root = inst.constructBinaryTreeWithPostAndPre(pre, post, 0, 0, post.length-1, map);

        System.out.println(root.val);

        String[] items = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(inst.evalRPN(items));

        String s = "aaa";
        List<String> dic = Arrays.asList("aaa","aa","a");
        inst.findLongestWord(s, dic);

        inst.change(amount, coins);
        inst.changeDfs(amount, coins);

        String ps = "abcabc";
        int[] next = inst.getNext(ps);
        for (int i = 0; i < next.length; i++) {
            System.out.print(next[i] + " ");
        }

        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] dest = {3, 2};
        boolean res = inst.hasPath(maze, start, dest);
        System.out.println(res);

        /*
        int[] nums = {2,3,3,3,4};
        int m = 2;
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        Arrays.sort(nums);
        inst.combination(nums, res, temp,0, m);
        System.out.println("total combination number is: " + combNum);
        System.out.println("all combination sets are: ");
        System.out.println(res);
        res.clear();
        temp.clear();

        inst.permutation(nums, res, temp, used, m);
        System.out.println("total permutation number is: " + permNum);
        System.out.println("all permutation sets are: ");
        System.out.println(res);
        res.clear();
        temp.clear();

        String[] words = {"Hello", "Alaska", "Dad", "Peace"};
        String[] res = inst.findWords(words);
        System.out.println(res);

        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);

        int[] ret = inst.findMode(root);
        for (int i : ret) {
            System.out.print(i + "  ");
        }
        */
    }

    public class Point implements Comparable<Point> {
        int x;
        int y;
        int dis;
        String path;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.dis = Integer.MAX_VALUE;
            this.path = "";
        }

        public Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.path = "";
        }

        public Point(int x, int y, int dis, String path) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.path = path;
        }

        public int compareTo(Point point) {
            if (this.dis != point.dis) {
                return this.dis - point.dis;
            } else {
                return this.path.compareTo(point.path);
            }
        }
    }
}
