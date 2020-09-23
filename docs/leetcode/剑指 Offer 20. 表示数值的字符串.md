### 有限状态自动机模型

![状态自动机模型](https://images.gitee.com/uploads/images/2020/0902/104101_2e3c61dc_5268452.png "state machine.png")

- 结束状态为：[2,3,7,8]

### 代码

```python
class Solution:
    def isNumber(self, s: str) -> bool:
        states = [
            {' ': 0, 's': 1, 'd': 2, '.': 4},
            {'d': 2, '.': 4},
            {'d': 2, '.': 3, ' ': 8, 'e': 5},
            {'d': 3, 'e': 5, ' ': 8},
            {'d': 3},
            {'s': 6, 'd': 7},
            {'d': 7},
            {'d': 7, ' ': 8},
            {' ': 8}
        ]
        p = 0
        for c in s:
            t = '?'
            if '0' <= c <= '9':
                t = 'd'
            elif c in '+-':
                t = 's'
            elif c in 'eE':
                t = 'e'
            elif c in ' .':
                t = c
            else:
                t = '?'
            if t not in states[p]:
                return False
            p = states[p][t]
        return p in [2, 3, 7, 8]

```

