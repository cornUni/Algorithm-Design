class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


levelIterate = []


def arrToBST(arr):
    if not arr:
        return
    mid = (len(arr)) // 2
    root = Node(arr[mid])
    root.left = arrToBST(arr[:mid])
    root.right = arrToBST(arr[mid + 1:])

    return root


def printByLevel(node):
    if not node:
        return
    h = height(node)
    for i in range(1, h + 1):
        printLevel(node, i)
    for i in range(0, len(levelIterate)):
        if i == len(levelIterate) - 1:
            print(levelIterate[i], end='')
        else:
            print(levelIterate[i], end=', ')


def printLevel(node, level):
    if node is None:
        return node

    if level == 1:
        levelIterate.append(node.val)
    elif level > 1:
        printLevel(node.left, level - 1)
        printLevel(node.right, level - 1)


def height(node):
    if node is None:
        return 0
    else:
        lheight = height(node.left)
        rheight = height(node.right)

        if lheight > rheight:
            return lheight + 1
        else:
            return rheight + 1


lines = []


while True:
    line = input()
    if len(line) == 0:
        break
    else:
        lines.append(line)

arr = [int(num) for num in lines]
arr.sort()
print('[', end='')
printByLevel(arrToBST(arr))
print(']', end='')