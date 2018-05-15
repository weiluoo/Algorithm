date: 5/8/2018

link: http://www.1point3acres.com/bbs/thread-417522-1-1.html 
1. Reverse polish notation evaluation, 逆波兰表示法求值，其实就是很常规的stack题，也保证了没有特殊情况，正常写。
2. Check一个字符串能不能由另外一个字符串中的字符组成，其实就是弱化版的anagram，开个数组或者hashmap记一下数量就完事了。之后讨论了一些特殊情况，比如字符串太长不能放进memory之类的。
3. 一些人坐在椅子上，新来的人要坐在离两侧人尽量远的位置，然后要求在线算法。这是一道面经题，用heap维护剩余区间就可以了。然后在线其实是个follow up。
真.follow up: 给你几个正方体，每个正方体上有些字母，要求check能否用这些正方体拼出一个指定的单词，每个正方体最多使用一次。这个题其实是二分图的完美匹配，背一个匈牙利算法的板子就完事了。面试官小姐姐说是bonus了，所以大家也不必刻意去准备。
4. 第一题，一个二维01矩阵表示一个图，所有1都与第一行连通，也就是说从任何一个已有的1出发，一定可以到达第一行的一个1。然后现在删掉一个1，要求继续删掉所有不再与第一行连通的1，修改原数组。其实是有很多信息可以用的，但是直接裸写一个DFS好像就完事了，反正复杂度是O（mn），面试官说这样就OK，他不在乎复杂度。


date 5/9/2018

link: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=400951&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

1.黑哥哥 
input: 一些字符串words， 一个目标字符串， 要求返回words里 包含了所有目标字符串里的所有char 的词中最短的那个。
eg:  words = [study, haha, stone, school, star, store]  target = "rts", 需要返回 的词是star, 因为star 包含了所有rts, 同时也是最短。 

follow-up: 多种方法优化这道题的方法， 楼主先排序 （要求写出查找的平均时间复杂度）， 
楼主还答了可以把words 存成 s: study, stone, school, star, stor; t : star, stone, store..... 这样的map, 好处是我们查找的时候只需要看target里出现过的char所对应的词，然后找并集，在目标字符串很小的情况下有可能会省很多时间。

2.白姐姐 
用preorder和postorder建树，这道题出来时我说好像没有inorder是做不出来的，然后聚了个例子，面试管说那就假设总是有left subtree的，然后楼主用recursion写了。完了分析时间复杂度，问怎么优化（hashmap 存一边postorder里的val 对应的Index）

3. 白大哥
类似乐扣伞就无 但不一样的是这个api不能存query, 不负责接收query,不负责发送，只是会每1秒让n个isAllowed（） 返回true. 这题我当场有点懵，我就说了那就写一个count和clear, 然后每秒清零count。但这样做会造成每秒前10个可以，会形成每秒一个峰值。面试官问可不可以有一个更平均的分布，我说那就每1/n秒返回一个true, 然后清零... (我知道这样做很笨，但当场没有想到更好的方法。面试官一直强调说不能存任何和query有关的东西（除了计数），只负责yes or no然后保证这个rate的正确性)，写完码答了怎么unit test

4.  话很多的白姐姐
这个姐姐是我面得最开心的一轮，她整个人很活波，一直笑，让我放松了很多。
给一个char stream,  有next(), 和hasNext(), 两个API。 给一些字符串作为目标字符串。要求每当char stream里出现目标字符串任何一个词，就打印这个词。
比如目标 'abc, att, bba, bc, abce', 然后我们对char stream call next， 出来的一些char 是 t, a, b, c, e, t.... 我们需要打印 abc, bc, abce 
这题用字典树，比较麻烦的是需要在字典树里存多个指针，然后每次出来一个char，就写一个Loop来更新所有现有的指针就可以给了

date 5/10/2018

date 5/11/2018
followed by previous from date 5/9
links: 
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=400951&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

http://www.1point3acres.com/bbs/thread-417522-1-1.html 

1. 乐扣巴以无 
这题我面试前一天看过一眼，因为当时是乐扣最新的一道hard，（其实一点不难），但前一天只是想了一下，没有练习码。不过面试官说出考题的时候还是很激动的，感觉比较胸有成竹了吧。
做完后写了三个unit test, 白哥看了时间，还有10多分钟，他让我写了两个javascript里的call back function 给他看一下，问了一下我平时遇到bug怎么debug之类的就聊完了。

2. reconstruct BST using sorted array. 这是LC的老题了吧，每次取中点然后递归。
Follow up, 要求建一个complete BST, 就是说每行都得从左边开始填，填满才能有下一行。这个麻烦一点，我打了个表归纳了一下两侧应该有多少个数，写了个函数来计算当前数组应该给左边分配多少个数。

3. 一个正则表达式，B*  A*  C*, 然后给满足这个RE的字符串统计A的个数。二分就可以了。

4. 给你一个字符串和一个字典，要求你求字典里是给定字符串substring的最长字符串。O（n+\sum m）, 用waiting list处理。 面试小哥和我纠结了很久为什么BFS不好，说不出话。

5/12/2018
link: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=423481&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
今天的题感觉偏难 周末就…多看看吧 lol

第一轮：印度小哥哥
题目是，
给了一个table,  会有很多colum，然后让写一个类似query的method。
比如，这个table里会有“+”
table：
name,           compay,               address
Lily+Mary      Google + Apple        CA
那么这一行就equal to 以下这四行
name,           compay,               address
Lily             Google                     CA
Mary            Google                   CA

Lily             Apple                   CA
Mary            Google               CA

然后让你写一个query method， 
比如 find ( name = Lily or Mary) AND (copany == Google)， 这个query有规则，每一个attribute内部只能用or链接， 不同attribute之间用and链
对于以上这个query，要return下面两行。。
Lily      Google + Apple        CA
Mary    Google + Apple        CA

于是楼主想到了之前做quantcast家oa时遇到过类似的题，，我就说可以用trietree做，建一个nested hashmap，再然后得到结果。 面试官好像没料到我会这么做，觉得是个interesting method，很感兴趣的样子，要我coding， 但坑爹的是，代码量太大了，我没写完。。。。期间，中间面试官质问我了一次觉得我的方法可能不够general，不好scale up，但在我解释完之后，他都说yes, you are right, this makes sense, this could scale up, continue.  但问题就是这样解决这个问题代码量确实有点大。。首先要parse这个table放进trietree，然后再parse这个query string，得到一个list<list<integer>>，内层是同一个colum下的or的关系的结果，外层是不同colum间and的结果，  然后再分析这个nested list。。代码量真的太大。。最后没写完，只写到了 nested list这里。。面试官打断我说时间不够了，让我口述后面的想法，然后就结束了这一轮。。我怀疑他原本的思想不是要用trie？

第二轮，白人小哥哥
给了两个很大的file， 第一个file 大概是500gb，里面是一篇文章，由很多word和空格组成。第二个file也很大，里面大概是5000个词，被空格分开，这些是要被隐蔽的词汇。
然后需要一边读第一个file，一边处理它，遇到了要隐蔽的词就输出“***”，其余的词则正常显示。
我依然说是可以用trie做。。把要隐蔽的词都放进trie里，然后读第一个file。。
面试官让我写了下这个trie的class，我写了inset（string）方法，正准备写search（string）方法，面试官说不用写了，他相信我知道怎么写好这个class，要我move on 写别的部分。
问题是处理file的那些api我不太记得了，，面试官也不太记得了，他现场查了api告诉我。。然后我再规定的时间写完了。让我问了问问题。结束时他说he likes my solution.

第三轮，国人大哥
这轮跪了，问了个很简单的问题，大概就是根据一些rules来truncate 一个很长的string到长度为k，我一开始给想复杂了，用了sort，comparator什么的，是O(nlogn). 让我想更快的方法。我就给了个 O(nlogk)的方法，又是用treemap，又是用deque的。。他说还是不够。。然后我才相处了O(N)的方法。但此时为了描述之前的方法花了太多时间（我讲的时候，他有点跟不上我的方法，于是我讲了很久）,此时大概只剩10min coding了，我勉强coding完，代码还不够简洁，他临走时还指出有部分我的代码太啰嗦。。。而且这个o（n）的方法其实相当简单，相当straightforward，我严重怀疑他其实有准备第二个问题问我。。只是我用太多时间了没来得及问我。。。这一轮感觉有点跪了。。

第四轮，印度大哥
这位大哥是个seti，就是干测试的，问的问题也很偏测试。
这轮给了两道题，都不难，第一题是add two big numbers, 写完代码后关于 test case 问的很细致。。我一直说，他也一直不喊停。。我就说了很多很多不同情况的test cases
第二题是个dp，countSum（int sum, int k), 比如 countSum(5, 2), 就是2个数加起来等于5，数有多少种情况。
我说dp，但我第一反应是一个 O(SUM*SUM*K)的dp，我一边说一边感觉自己肯定想复杂了，但他说“没关系，你之前做了一提了，这是第二题，三次方的复杂度我是可以接受的，请开始code”，但我觉得肯定有二次方的dp，我就说让我再想想，他说建议我把matrix画出来（其实就是在给我hint）。我画出来后就明白了这是个 o(sum*k)的dp，写完后他又问了我下如何测试。最后聊了聊天，结束了面试。。


5/14/2018
link: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=424395&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio&page=1

1.找文章里出现频率最高的十个连续单词对

2.系统设计，分布式key value 系统，估算吞吐量没答好

3.有一个二叉树，每个边上有值，怎样选择边cut，能够以最小代价cut 掉所有叶子节点。

午饭的哥们没来，第三轮面试的哥们带我去吃饭，感觉吃饭时略显尴尬，估计有负面影响

4.N个人给K个candidate 投票，N个人给出K个candidate的偏好排序，得到一个矩阵：例如4个人给3个人投票：
[[1,2,3],
[1, 3,2],
[2,1,3],
[3,1,3]].

然后选择第一列，找出得票最少的candidate排除，然后得到新的矩阵，继续上述步骤，直到最后返回最后剩下的candidate，这题中间有个两个for循环搞糊涂了，卡壳了一阵，还有一个edge case被面试官指出，最后一个followup是，如果N很大，K很小怎么优化，额外给了5分钟没想出来。。。
5.表达式求值，表达式有运算符*和+
(+ 4 5) 得9
(* 4 5)的20
(+4(* 4 5))得24
直接递归硬写出来了，followup是如果有if操作怎么办，这个哥们很严肃，我笑他不笑的感觉。

第一个哥们我拍了他好多马屁，貌似很开心，第二个说了两次I am happy，不知道是不是真的happy，第三个followup挑战了一下复杂度，和他吃饭聊不是很开心，本人是个粗人，对方貌似挺斯文的，貌似不太喜欢吃饭时候聊天；第四个喜欢打断我，这一轮也是写的比较慢，而且有个比较严重的edge case没考虑了，被提示改了多次代码，最后的followup没答上，说留给我homework；第五个全程严肃，盯着我代码看了很久，说应该没问题

5/15/2018
link: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=423736&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3086%255D%255Bvalue%255D%3D8%26searchoption%255B3086%255D%255Btype%255D%3Dradio%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio&page=1

第一轮: 印度大叔 人很好
定义 n-straight 指连续的n个数字, 输入是一个integer array 和n , 要求返回array 满不满足 n-straight 的要求:
eg 3-straight  [1,2,3,5,6,7]返回true, [1,2,3,4,5] 返回False
follow up: 如果n-straight 指至少n个连续的数字, 应该怎么写函数. visit 1point3acres.com for more.
-google 1point3acres
第二轮 不知道哪里的大叔

给一个integer array 表示不同符号的个数, 和输入n, 返回所有可能的n个符号的组合(不在乎顺序)
follow up: 返回这些组合的个数, 优化时间空间复杂度

第三轮 一个第一次当面试官的小哥. 
给一个integer array, 表示每辆车在路上的速度, 假设他们都往同一个方向开, 开的快的会被开的慢的卡住,  问你最后整个路上的车, 会被分成几组 ,每组有几辆车.
follow up, 往这个车队里加一辆车(所有可能的位置), 返回所有可能的下一步的结果

第四轮, 两个字符串, 一个字符串比另一个多一个字母, 其余出现顺序相同,返回那个字母,
follow up: 出现顺序不一定相同, 返回那个字母,
follow up: 如果字符串特别大, 怎么办?

5/16/2018
link: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=425011&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

第一轮 感觉是白人小哥 没有口音
上来先聊了一下简历里的一个项目 小哥似乎对GPU并行运算蛮感兴趣 简单聊了一下
然后上题：LC444
输入只有一个subsequence的list 假设可以reconstruct成唯一的original sequence 要求返回这个original sequence
拓扑排序秒掉了 之后讨论了一下edge case 又问如果结果不唯一 如何判断 如何处理

第二轮 国人小姐姐
自我介绍之后直接做题 LC399
听完题之后我说用DFS做 小姐姐说可以先构建出全部的available division 然后再返回query的结果 感觉像是把query list拿出来做成API
我想了一下没想到好方法 就还是用DFS构建出全部division
最后写完了 分析了一下复杂度 不过后来回想了一下小姐姐的意思应该是用Union Find做的 面试之后写了Thank you note有提到Union Find的解法:)


