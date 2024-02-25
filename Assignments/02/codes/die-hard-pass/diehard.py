n = int(input())
memo = []
result = []

primary = []




def check_number(number):
    if not is_prime(number):
        return False
    else:
        number //= 10
    while number>0:
        if memo.count(number) == 0:
            if is_prime(number):
                memo.append(number)
            else:
                return False
        else:
            continue

        number //= 10
    return True 


def is_prime(n):
  n = int(n)
  if n==1:
    return False
  for i in range(2,n):
    if (n%i) == 0:
      return False
  return True


for i in range(10**(n-1), 10**(n)):
    # print(i," is ", end="")
    # if check_number(i):
    #     print(" strong")
    #     result.append(i)
    # print("weak")
    if is_prime(i):
        primary.append(i)
for i in range(n):
    temp = []
    for j in range(len(primary)):
        if is_prime(primary[j]):
            temp.append(float(primary[j]/10))
    primary = temp

for i in primary:
    print(int(i* (10 ** n)))