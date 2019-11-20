import unittest


class AB:

    def createString(self, N, K):
        start = 'B' * N
        return self._makeString(N, K, start)

    def _makeString(self, N, K, start, inserts=0):
        if K < 0 or N == 0:
            return ""
        if K == 0:
            return start
        if K + inserts < N:
            index = len(start) - K - inserts
            return ('A' * inserts) + start[:index - 1] + 'A' + start[index:]
        tmp = start[1:]
        newK = K - len(tmp) + inserts
        if newK >= K:
            return ""
        return self._makeString(len(tmp), newK, tmp, inserts + 1)


class TestA0Paper(unittest.TestCase):

    sut = AB()

    def count_b_in_string(self, s):
        return sum(map(lambda x : 1 if 'B' in x else 0, s))

    def test_count_b_in_string(self):
        self.assertEqual(self.count_occurences('ABB'), 2)
        self.assertEqual(self.count_occurences('AABAB'), 5)

    def count_occurences(self, s):
        split = list(s)
        count = 0
        for index, value in enumerate(split):
            if value == 'A':
                count += self.count_b_in_string(s[index + 1:])
        return count


    def test_1(self):
        self.assertEqual(self.count_occurences(self.sut.createString(3, 2)), 2)

    def test_2(self):
        self.assertEqual(self.count_occurences(self.sut.createString(3, 0)), 0)

    def test_3(self):
        self.assertEqual(self.count_occurences(self.sut.createString(5, 8)), 0)

    def test_4(self):
        self.assertEqual(self.count_occurences(self.sut.createString(10, 12)), 12)

    def test_5(self):
        self.assertEqual(self.count_occurences(self.sut.createString(30, 66)), 66)

if __name__ == "__main__":
    unittest.main()