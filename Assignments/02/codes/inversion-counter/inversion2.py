def mergeSort(arr, n):
    
    result = [0]*n
    return mergeSort_handler(arr, result, 0, n-1)
 
def mergeSort_handler(arr, result, left, right):
 

    inv_count = 0
 
 
    if left < right:
 
 
        mid = (left + right)//2
 
 
        inv_count += mergeSort_handler(arr, result,
                                left, mid)
 
        inv_count += mergeSort_handler(arr, result,
                                mid + 1, right)
 
 
        inv_count += merge(arr, result, left, mid, right)
    return inv_count
 
 
 
def merge(arr, result, left, mid, right):
    i = left     
    j = mid + 1 
    k = left     
    inv_count = 0
 
 
    while i <= mid and j <= right:
 
 
        if arr[i] <= arr[j]:
            result[k] = arr[i]
            k += 1
            i += 1
        else:
            result[k] = arr[j]
            inv_count += (mid-i + 1)
            k += 1
            j += 1
 
    while i <= mid:
        result[k] = arr[i]
        k += 1
        i += 1
 
    while j <= right:
        result[k] = arr[j]
        k += 1
        j += 1
 
    for n in range(left, right + 1):
        arr[n] = result[n]
 
    return inv_count


if __name__ == '__main__':
    n = int(input())
    arr = []
    for i in range(n):
        arr.append(int((input())))

    result = mergeSort(arr, n)
    print(result % 100000)