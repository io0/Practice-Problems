#include <stdio.h>
#include <string.h>

char *mystrncpy ( char * destination, const char * source, size_t num )
{
int i;
char *front = destination;

for (i = 0; i<num && *source != '\0'; i++)
{
*destination++ = *source++;
}

int a = num - i;
while (a--)
{
*destination++ = '\0';
};

return front;
}


int mystrncmp ( char * s, char * t, size_t num )
{
for (int i = 0; i<num; i++)
{
if (*s++ != '\0' && *t++ == '\0') return 1;
else if (*s++ == '\0' && *t++ != '\0') return -1;
}
return 0;
}


char *mystrncat ( char * destination, const char * source, size_t num )
{
int i;
char *front = destination;
while (*destination != '\0') *destination++;

for (i = 0; i<num && *source != '\0'; i++)
{
*destination++ = *source++;
}
*destination++ = '\0';                                                         /* Append a null character */

int a = num - i - 1;
if (a!= -1)                                                                    /* If the number of characters required exceeds the size of source */
{
while (a--)
{
*destination++ = '\0';
};
}
return front;
}


int main()
{
char s[1000], t[1000];
printf("Enter the first string:\n");
gets(s);
printf("Enter the second string:\n");
gets(t);

printf("strncpy is %s\n", mystrncpy( t, s, 7));
printf("strncat is %s\n", mystrncat( t, s, 7));
printf("strncmp is %s", t);
if (mystrncmp( t, s, 8)  == 0) printf(" = ");
if (mystrncmp( t, s, 8)  == 1) printf(" < ");
if (mystrncmp( t, s, 8)  == -1) printf(" > ");
printf("%s\n", s);
}
