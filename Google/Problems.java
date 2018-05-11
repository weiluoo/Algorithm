
    public TreeNode constructBinaryTreeWithPostAndPre(int[] pre, int[] post, int preIdx, int postL, 
                                                      int postR, Map<Integer, Integer> idxMap) {
        if (rootIdx >= pre.length || postL > postR) return null;
        TreeNode root = new TreeNode(pre[rootIdx]);
        rootIdx++;
        
        if (rootIdx >= pre.length || postL == postR) return root;
        int idx = idxMap.get(pre[rootIdx]);

        root.left = constructBinaryTreeWithPostAndPre(pre, post, preIdx+1, postL, idx, idxMap);
        root.right = constructBinaryTreeWithPostAndPre(pre, post, idx+1, idx+1, postR-1 , idxMap);
        return root;
    }
    
        public static int rootIdx = 0;

        public static void main(String[] args) {
        Problems inst = new Problems();

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
        }
