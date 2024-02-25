inversion = 0

def merge_sort(arr, left, right):
    inversion_count = 0

    if left <right:
        mid = (left + right) // 2

        inversion_count += merge_sort(arr, left, mid)


        inversion_count += merge_sort(arr, mid+1, right)


        inversion_count += merge(arr, left, mid, right)

    return inversion_count


    

def merge(arr, left, mid, right):
    left_arr = arr[:mid+1]
    right_arr = arr[mid+1:]

    # result = [0]*len(arr)
    i = 0
    j = 0
    k = left
    inv = 0

    while i < len(left_arr) and j<len(right_arr):
        if left_arr[i] <= right_arr[j]:
            arr[k] = left_arr[i]
            k+=1
            i+=1
        else:
            arr[k] = right_arr[j]
            k+=1
            j+=1
            inv += (mid +1) - (left + i)
        
    while i<len(left_arr):
        arr[k] = left_arr[i]
        k+=1
        i+=1
    while j<len(right_arr):
        arr[k] = right_arr[j]
        k+=1
        j+=1
    
    return inv
        


        
    

if __name__ == '__main__':
    n = int(input())
    arr = []
    for i in range(n):
        arr.append(int((input())))
    
    print(merge_sort(arr, 0, len(arr)-1) % 100000)
    