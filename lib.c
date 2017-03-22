long fib(long y)
{
if (y <= 2) return 1;
else return fib(y-1) + fib(y-2);
}

void sort(int data[], int x)
{
int i, j, temp;
for (i = 0; i < x-1; i++)
{
	for (j = 0; j < x-i-1; j++)
	{
		if (data[j]>data[j+1])
		{
		temp = data[j];
		data[j] = data[j+1];
		data[j+1] = temp;
		}
	}
}
}
