
# Java Synchronized Visibility Guarentee
![Java synchronized block](src/synchronizedBlock/img/java-synchronized-3.png)
1. Java Synchronise Visibility Guarentee

# Java Happens Before Guarentee
![Java synchronized block](src/synchronizedBlock/img/java-synchronized-4.png)
1. Compiler is allowed to do reordering which hampers visibility guarentee

# Java Synchronised Limitations
1. Only one thread can enter synchronised block at a time.
2. There is no guarentee about the sequence in which waiting threads gets access to the synchronised block
3. No Fairness(Starvation) to thread which is waiting for his access to synchronised block

![Java synchronized block](src/synchronizedBlock/img/java-synchronized-7.png)

1. Thread entering in synchronous in different JAVA-VM are not synchronized