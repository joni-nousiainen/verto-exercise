# Exercise instructions

Source: http://www.mol.fi/tyopaikat/tyopaikkatiedotus/haku/8845235_fi.htm

Design and implement REST API supporting the operations described below. Use Java 8 or Scala and either Java EE or Play framework. Your backend storage may be non-persistent in-memory storage. Document your API and your code. Write tests. Send the whole package to us with detailed instructions for compilation and deployment. Describe what was the most difficult part of the assignment and why. If you need some clarifications with respect to the exercise, do not send us questions. Make an assumption, and send the list of assumptions made together with your solution.

Operations to implement:

1. "upload" -- upload a set of strings to the server, verify its validity (it must be a set, so no duplicate strings are allowed)
2. "search" -- find all the sets containing given string
3. "delete" -- delete the specified set of strings from the server
4. "set_statistic" -- return the statistics for one of the previously uploaded sets (number of strings, length of the shortest string, length of the longest string, average length, median length)
5. "most_common" -- find the string used in the most sets; if there are a few such strings, return them all, sorted alphabetically
6. "longest" -- find the longest string in all the sets; if there are a few such strings, return them all, sorted alphabetically
7. "exactly_in" -- find the string which is present in exactly given number of sets
8. "create_intersection" -- create a new set of strings which is an intersection of two previously uploaded sets

For bonus points (if you want to be considered for more senior position):

1. Implement one more endpoint: "longest_chain" -- from the previously uploaded sets find the longest chain of strings such that:

every next string starts with the same character as the previous one ends with
every next string belongs to the same set as previous one, except one jump to another set is allowed
specific string from specific set may be used only once

Example:
Set 1: foo oomph hgf
Set 2: hij jkl jkm lmn
Set 3: abc cde cdf fuf fgh

The longest chain is: abc - cdf - fuf - fgh - (set changed here) - hij - jkl - lmn

2. Explain what is computational and space complexity of your implementation of
"longest_chain" operation.

3. Design and implement some kind of stress-testing for your application.

# Assumptions

> Use Java 8 or Scala and either Java EE or Play framework.

I assumed it's enough to use just some parts of the Java EE spec but other frameworks and libraries can be used as well.
