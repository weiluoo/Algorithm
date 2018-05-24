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
follow up: 如果n-straight 指至少n个连续的数字, 应该怎么写函数.

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

3. 难 面试官不要求具体实现
给定一个矩阵 里面有多个人和多个自行车 每个人最多占一辆自行车 要求输出所有人匹配自行车路径和的最小值
思路：带权二分图的匹配
将问题转化成二分图：一波是人 一波是自行车 
然后每个人到所有自行车都能形成一条边 每条边有权重(距离) 然后求解最优匹配:使得边的权重和最小

5/20/2018
link: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=425692&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

Onsite:
1. 白人小哥
汇率转换。应该是道面经题，但是我在地里没有找到。给定这样一个list：[[USD, 0.85, EURO], [EURO, 130, Yen]], 求任意两个货币之间的汇率。先用map和dfs做的，面试官问我这样做会有什么问题，我说stack会over flow，后来改成了union find。followup是如果想要query常用的几个货币之间的汇率应该怎么做。答曰用cache

2. 白人小哥
这轮面的不好。给一个int数组，return current element 到 next greater element 的距离。比如input 是[2, 1, 4, 700, 5]，return[2, 1, 1, -1, -1]。先用brute force 做的。后来改成了dp，dp的时候很多edge case都没考虑到，面试官提醒了很多次。面试结束之后搜到比较好的方法是stack。

3. 国人大哥
利口而欺凌，followup 是 k closest elements. 

4.毛子大叔
给一个2d boolean array代表empty seats。每次进来一个query寻找k个连续的empty seats。这k个seats必须是上下左右相邻的，并且seats 是randomly selected。我是keep了一个empty LinkedList，每次random一个index，找到这个index上的empty spot and run dfs，然后 recursively寻找它的上下左右spot。

5/21/2018
link: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=425526&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

第一轮： Find first missing number eg.  [2, 3, 5,6,8]; = > 4 follow up 是 find kth missing number.
第二轮： 一个list和一个set 存不存在continues subarray contain所有set里的  数量也是一样。  然后也没问第二个问题 一直再给我不同test case做测试，然而面试管就是没找到bug.....搞的我好慌。。。
第三轮： Find the number of identical subtree. 
第四轮： sliding window 求mean 

楼主是个本科的**- -。。。题目不难。但是follow up time 和space有点尴尬。有两个面试管喜欢精确的 还有一个就直接O（N）这样就好了。

5/22/2018 
link: 
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=425304&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

第一轮是面经题，就是大家玩过宝石迷阵吗，没玩过的你玩一下就知道了，问题是你怎么样生成一个宝石迷阵的初始化的棋盘，意思就是你打开游戏的那一刻的整个棋盘的样子。

第二轮基本和all anagrams一样，就是换了种说法。

第三轮类似于蠡口刘武酒。

第四轮类似于蠡口刘武饵。

5/23/2018
link:
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=425363&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

电面：abc面试官，第一题：给出一个int array，找出其中一个index使得index左边所有的数加起来等于index右边所有的数加起来（左右not inclusive）。时间复杂度要求O(n)，空间复杂度O(1)。第二题：给定两个string， 判断这两个string是否有且仅有两个字母调换了顺序，举例：abcd和acbd （b和c调换），affhsgn和afghsfn（f和g调换了）. 

昂赛特：
1. 白人女面试官，先是OOD，设计一个timer，可以设定总计时时长，然后每过一分钟就打印一次剩余的时间，直到计时结束。第二题：给若干个string，get k combination, 例子：{run，age，app, cat, dog}  k = 3, output:[{run, age, app}, {run, age, cat}, {run, age, dog}, {run, app, cat} ....]

2. 利口上面可能有，但是我忘了题号，题目是：给一个chars的顺序，然后判断给定的char array里面的所有的chars之间是否满足这个顺序。举例：dict：[d, a, c, f, j]  满足顺序的chars: {d, a, f, f, j} 不满足顺序的chars：{a, c, c, d, j}或者{c, f, j, j, d, a}. 要求时间复杂度是O（n），n是chars的长度（假设chars的长度大于dict长度）。 第二题，利口 久留。

3. 感觉像是俄罗斯面试官，面的很难。利口奇琪司的变形。station的坐标都是int，然后要求最后返回所有的station（包括原先的和加建的，然后所有的坐标都是int）。也就是说如果开始是[1，4，5]，加1个station，最后就是返回[1，2，4，5]或者[1，3，4， 5]。这里面的corner case handle起来还是挺多的，最好写一下。

4. abc面试官，第一题：给定一个multiple tree，以及需要删除的多个nodes，要求返回一个node的list，以便在nodes被删除之后可以找到这些nodes的child。
例子：
                 a
       /      /     \      \
     b      d       c      f
  /   \   \
h     z   i 
假如删除b和f，返回{a, d, c, h, z, i}
假如删除a和b，返回{d, c, f, h, z, i}
可以用level traverse解。

第二题：
给定一个multiple tree，除了parent指向child，同一层的children：第一个child指向第二个，第二个指向第三个。。。以此类推，最后一个指向第一个，形成一个回路。
求出最长的路径。楼主用的暴力破解法，用recursion全部过了一遍，找出最长的，面试官表示满意。

加面：
1. 烙印（我发现一般烙印出题都不会特别难，比较规矩），类似two sum，给出一个int array，找出两个int相加合最接近target 的sum。先sort，然后左右two pointers。写了很多test case，问了test的一些问题，例如在什么情况下，代码没有变化，但是有时test能过，有时候过不了（多线程干扰，dependency稳定性等等）。
2. 利口 琪尔耳。写了很多test case。

5/24/2018
link:
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=426676&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

1. 类似之前一个面经题。只不过不是从天花板吊起来，而是从地上堆起来的盒子。给定一个grid和目标盒子的位置，问去掉目标盒子之后，哪些盒子会动。
例如：
OXXX
OOXO
OOXO

X是盒子，O是空气，相邻的盒子之间会有粘性。如果去掉第一行第三列的X，它左右相邻的两个会因为没有重力支持掉下来。


2. 给定k，和一个二进制序列，判断该是否包含全部可能长度为k的序列。
例如，11001包含了所有k=2的可能的二进制序列（00，01，10，11）。
followup，不是判断，而是生成序列（里口原题）

3. 里口原题，二叉树从右边看，求每层能看到的节点。
还问了该题BFS，DFS实现的时间和空间复杂度

4. 给定两个字符串list A, B，返回第二个list中的每个元素是否是第一个list中某个字符串的subsequence。
比如['aggressive', 'abc'], ['agr']，返回[True]。
followup，如果A很大，并且B的字符串长度不超过6，有没有更好的办法？（可以preprocessing）
里口伞久尔

5/25/2018
link:
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=425410&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3086%255D%255Bvalue%255D%3D8%26searchoption%255B3086%255D%255Btype%255D%3Dradio%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio&page=1

第一轮：时间比较早所以面试的三哥迟了一会儿，然后就把我带去了面试的小会议室，途中问了我些什么，但有口音且说话嘟囔。只能表示抱歉。            

三哥一上来就来了道扫地机器人，还好是个高频题，不需要理解题意。因为之前准备了所以假装思索过后就开始写代码，每写两行三哥都要打断问我在干什么，顺带表示时间有限。还好最后写完了。（有点担心他是故意拖我，最后两分钟还要打断我让我详细解释一下刚才的两行在干嘛。）

第二轮：白人小姐姐，TF(boy)(?)组的。也是话不多说直接就放题目了，不过这轮很简单。

输出一颗树的 max level sum，不是二叉树。先让我定义 TreeNode 该怎么定义，顺带问问成员变量的访问权限。然后就用层序遍历秒了。然后接着问了问我会用什么样的 test case 来测试。
第二道是查找一串值是不是在一列 interval 里，interval 没有重叠并且排好了序。我表示 binary search 就可以，然后让实现了一下，也是秒了。然后聊了聊 TensorFlow

第三轮：白人小哥，问了简历的一个项目，然后上了道 DP

一堆卡片从一头拿，可以拿一二三张，两人轮流拿，求最高得分。小哥直接表示有负数的情况。我刚吃了饭有点懵，推出了正数的情况但是卡在了含负数的情况。然后小哥说那就先不考虑负数吧。随后还给了点 hint，然后就写出来了。并且让我在一个例子上过了一遍，问了问用什么 case 来测。

第四轮：中东大叔，说话带一点口音，并且很忙的样子，空闲期间一直在回邮件。

自行车那题。见过题目所以整个思路没有问题，但是实现起来还是有些细节问题，大叔问了些在点上的问题，我就发现了几个不太对的地方，然后改了。这跟扫地机器人一样是高频题了，大家准备的时候一定要自己写一遍代码啊。


5/28/2018
link:
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=400440&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D8%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

1. 在一堆兔子里给出两只，要求找兔子们的lowest common ancester，每只兔子有一个unique id和parents的list，兔子的辈分可能发生混乱。follow up是怎样降低复杂度。
2. 利特口的 耳领舞，没有让写代码直接follow up，假设有一个class里面有很多string，输入一个string假设跟class中的任何一个string满足的话就返回true。
    注意不是class中有一个string输入有多个string！做这题时非常struggle，最后想出解法都40分钟过去了，直接一点代码都没有写，五轮完后回顾还发现自己说的解法是错...
3. 利特口的 迩珥灵，1，2，3三部分
4. https://instant.1point3acres.com/thread/321726 第三轮
   假设有有限的自行车和人的坐标，注意两点：
   首先你并不知道boundry，并且如果人和车距离很远，这个图很稀疏的话，比如800米，以1为单位bfs或者dfs耗时非常长。
   其次不能遇到最近的就assign。就是不能把离第一个人最近的自行车直接assign给第一个人，有可能第二个人离这辆自行车更近第二个人会先拿到自行车；也不能把离第一辆自行车最近的人直接assign给这个自行车，有可能别的自行车离这个人更近。
   最后，这题的代码写着很烦，非常烦，请务必试着写下代码。这轮写完基础版本（不会有两个人距离一辆车的距离相等）已经用完了45分钟而且还有个小bug，于是没有follow up就结束了，求好运！
5. 给一个stream，stream里是word，设计一个数据结构，两个API，addWord和getTopK，第二个api返回截止到当前出现次数最多的k个词，不限制内存，follow up是降低第二个api的复杂度。
