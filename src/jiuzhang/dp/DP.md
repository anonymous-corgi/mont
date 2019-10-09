# DP:

+ ### Array Subsequence
  + [LeetCode 198. House Robber I](/src/leetcode/p151to200/LeetCode198HouseRobberI.java)
  + [LeetCode 213. House Robber II](/src/leetcode/p201to250/LeetCode213HouseRobberII.java)
  + [LeetCode 337. House Robber III](/src/leetcode/p301to350/LeetCode337HouseRobberIII.java)
  + [LeetCode 746. Min Cost Climbing Stairs](/src/leetcode/p701to750LeetCode746MinCostClimbingStairs.java)

+ ### Longest Increasing Subsequence
  + [LeetCode 300. Longest Increasing Subsequence](/src/leetcode/p251to300/LeetCode300LongestIncreasingSubsequence.java)

+ ### Subset Partition
  + [LeetCode 465. Optimal Account Balancing](/src/leetcode/p451to500/LeetCode465OptimalAccountBalancing.java)
  + [LeetCode 1066. Campus Bikes II](/src/leetcode/p1051to1100/LeetCode1066CampusBikesII.java) - Background Similar: [LeetCode 765. Couples Holding Hands](/src/leetcode/p751to800/LeetCode765CouplesHoldingHands.java)

+ ### Partition
  + [LeetCode 132. Palindrome Partitioning II](/src/leetcode/p101to150/LeetCode132PalindromePartitioningII.java)
  + [*LeetCode 188. Best Time to Buy and Sell Stock IV](/src/leetcode/p151to200/LeetCode188BestTimeToBuyAndSellStockIV.java)
  + [LeetCode 410. Split Array Largest Sum](/src/leetcode/p401to450/LeetCode410SplitArrayLargestSum.java)
  + [**LeetCode 871. Minimum Number Of Refueling Stops](/src/leetcode/p851to900/LeetCode871MinimumNumberOfRefuelingStops.java)

+ ### Range Properties
  + [**LeetCode 312. Burst Balloons](/src/leetcode/p301to350/LeetCode312BurstBalloons.java)
  + [*LeetCode 516. Longest Palindromic Subsequence](/src/leetcode/p501to550/LeetCode516LongestPalindromicSubsequence.java)

  ##### Game
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

  ##### Coin Change
    + [LeetCode 322. Coin Change](/src/leetcode/p301to350/LeetCode322CoinChange.java)
    + [LeetCode 518. Coin Change 2](/src/leetcode/p501to550/LeetCode518CoinChange2.java)


  |            | 0 -> capacity                    | 0 <- capacity                    | CAPACITY |
  |:---------- |:-------------------------------- |:-------------------------------- |:-------- |
  | items      | Reuse, Fix-Order(Combination)    | NO Reuse, Fix-Order(Combination) |          |
  | capacity   | Reuse, Multi-Order(Permutations) |                                  |          |
  | LOOP_FIRST |                                  |                                  |          |

  CAPACITY loops capacity -> 0: Avoids reuse  
  Loop items firstly: Avoids Permutations.

+ ### Two Range(Two Subsequence)
  + [LeetCode 072. Edit Distance](/src/leetcode/p051to100/LeetCode072EditDistance.java)
  + [LeetCode 087. Scramble String](/src/leetcode/p051to100/LeetCode087ScrambleString.java)
  + [LeetCode 097. Interleaving String](/src/leetcode/p051to100/LeetCode097InterleavingString.java)
  + [LeetCode 115. Distinct Subsequences](/src/leetcode/p101to150/LeetCode115DistinctSubsequences.java)
  + [*LeetCode 1143. Longest Common Subsequence](/src/leetcode/p1101to1150/LeetCode1143LongestCommonSubsequence.java)

+ ### Others
  + [LeetCode 403. Frog Jump](/src/leetcode/p401to450/LeetCode403FrogJump.java)
  + [LeetCode src/leetcode/p1001to1050/LeetCode1027LongestArithmeticSequence.java](/src/leetcode/p1001to1050/LeetCode1027LongestArithmeticSequence.java)
