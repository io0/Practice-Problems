#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include "lib.h"


int main (int argc, char *argv[])
{
int pid1, pid2;
pid1 = fork();

int x = atoi(argv[1]);
int y = atoi(argv[2]);

if (pid1 == 0) {
	printf("\nBubble Sort Process Created\n");
	printf("\nBubble Sort Process Started\n");
	printf("\nRandom Numbers Generated:\n");
	int data[x], num, i;
	time_t t;
	srand((unsigned) time(&t));

	for (i = 0; i < x; i++)
	{
		num = rand()%100;
		printf("%4d", num);
		data[i] = num;
	}
	
	sort(data, x);
	printf("\nSorted Sequence:\n");
	for (i = 0; i < x; i++) printf("%4d", data[i]);
	printf("\nBubble Sort Process Exits\n");
} else {
	pid2 = fork();
	if (pid2 == 0) {
		printf("\nFibonacci Process Created\n");
		printf("\nFibonacci Process Started\n");
		printf("\nInput Number: %d\n", y);
		long f = fib(y);
		printf("\nFibonacci Number f(%d) is %7d\n", y, f);
		printf("\nFibonacci Process Exits\n");
	} else { 
	printf("Main Process Started\n");
	printf("\nNumber of Random Numbers = %d\n", x); 
	printf("\nFibonacci Input = %d\n", y);
	printf("\nMain Process Waits\n");
	wait(pid1);
	wait(pid2);
	printf("\nMain Process Exits\n");
	}
}
}
