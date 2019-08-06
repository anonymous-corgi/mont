# DP:

+ ### Array Subsequence
  + [LeetCode 198. House Robber I](/src/leetcode/p151to200/LeetCode198HouseRobberI.java)
  + [LeetCode 213. House Robber II](/src/leetcode/p201to250/LeetCode213HouseRobberII.java)
  + [LeetCode 337. House Robber III](/src/leetcode/p301to350/LeetCode337HouseRobberIII.java)

+ ### Partition
  + [LeetCode 132. Palindrome Partitioning II](/src/leetcode/p101to150/LeetCode132PalindromePartitioningII.java)
  + [LeetCode 410. Split Array Largest Sum](/src/leetcode/p401to450/LeetCode410SplitArrayLargestSum.java)

+ ### Range Properties
  + [*LeetCode 516. Longest Palindromic Subsequence](/src/leetcode/p501to550/LeetCode516LongestPalindromicSubsequence.java)

  + ##### Game
    + [LintCode 394. Coins in A Line I](/src/lintcode/p351to400/LintCode394CoinsInALineI.java)
    + [LintCode 395. Coins in A Line II](/src/lintcode/p351to400/LintCode395CoinsInALineII.java)
    + [LintCode 396. Coins in A Line III](/src/lintcode/p351to400/LintCode396CoinsInALineIII.java)

+ ### Backpack
  + [BackPack I](/src/jiuzhang/dp/backpack/BackPackI.java)
  + [BackPack II](/src/jiuzhang/dp/backpack/BackPackII.java)
  + [BackPack III](/src/jiuzhang/dp/backpack/BackPackIII.java)
  + [BackPack V](/src/jiuzhang/dp/backpack/BackPackV.java)
  + [BackPack VI](/src/jiuzhang/dp/backpack/BackPackVI.java)
  + [BackPack VII](/src/jiuzhang/dp/backpack/BackPackVII.java)

  |            | 0 -> capacity                    | 0 <- capacity                        | CAPACITY |
  |:-----------|:---------------------------------|:-------------------------------------|:---------|
  | items      | Reuse, Fix-Order(Combination)    | NO Reuse, Fix-Order(Combination) |          |
  | capacity   | Reuse, Multi-Order(Permutations) |                                      |          |
  | LOOP_FIRST |                                  |                                      |          |

  CAPACITY loops capacity -> 0: Avoids reuse  
  Loop items firstly: Avoids Permutations.
