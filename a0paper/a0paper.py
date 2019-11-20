import unittest


class A0Paper:

    def canBuild(self, params):
        target = 2 ** len(params)
        sum = 0
        for i, p in enumerate(params):
            if p == 0:
                continue
            sum += p * (2**(len(params)-i))
            if sum >= target:
                return "Possible"
        return "Impossible"


class TestA0Paper(unittest.TestCase):

    sut = A0Paper()

    def test_1(self):
        self.assertEqual(self.sut.canBuild((0, 3)), "Possible")

    def test_2(self):
        self.assertEqual(self.sut.canBuild((0, 1, 2)), "Possible")

    def test_3(self):
        self.assertEqual(self.sut.canBuild((0, 0, 0, 0, 15)), "Impossible")


if __name__ == "__main__":
    unittest.main()