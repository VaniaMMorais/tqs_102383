# LAB6

## LAB6_1
| Issue | Problem Description | How To Solve |
| --- | --- | --- |
| _Security HotSpot_ | Using pseudorandom number generators (PRNGs) is security-sensitive. | Use a cryptographically strong random number generator (RNG) like "java.security.SecureRandom" in place of this PRNG.  |
| _Code Smell_ |  Passing message arguments that require further evaluation into a Guava com.google.common.base.Preconditions check can result in a performance penalty. That’s because whether or not they’re needed, each argument must be resolved before the method is actually called. | Instead, you should structure your code to pass static or pre-computed values into Preconditions conditions check and logging calls. |
| _Code Smell_ |  A for loop stop condition should test the loop counter against an invariant value (i.e. one that is true at both the beginning and ending of every loop iteration). Ideally, this means that the stop condition is set to a local variable just before the loop begins. | Stop conditions that are not invariant are slightly less efficient, as well as being difficult to understand and maintain, and likely lead to the introduction of errors in the future. |