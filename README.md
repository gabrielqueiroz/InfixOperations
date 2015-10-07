# Evaluate Infix Operations

* **About**

Infix notation is the notation commonly used in arithmetical and logical formulae and statements. It is characterized by the placement of operators between operands – "infixed operators" – such as the plus sign in "2 + 2" (From Wikipedia, https://en.wikipedia.org/wiki/Infix_notation)

* **How it works**

It uses a recursive method based in the priority table below, evaluating the values in a stack until it found postfix-output expressions.

The program accepts all the operators, any lenght of number and negative numbers.

```
Priority Table for evaluation
Precedence Level	||	Operator(s)
1 Lowest Priority	||	-,+
2 Normal Priority	||	*,/,^
3 Highest Priority 	||	(,)
```
* **Author**

Developed from Gabriel Queiroz

University Of New Orleans. Intro of Computer Theory - CSCI3102 - Fall 2014.
