import unittest


class BalancingTrees:

    def minCost(self, p, w):
        return 1


class TestBalancingTrees(unittest.TestCase):

    sut = BalancingTrees()

    def test_1(self):
        self.assertEqual(self.sut.minCost((0, 0, 0), (3, 1, 2, 1)), 1.0)

    def test_2(self):
        self.assertEqual(self.sut.minCost((0, 0, 1, 1), (0, 4, 7, 1, 2)), 1.0)

    def test_3(self):
        self.assertEqual(self.sut.minCost((0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3), (0, -1, 2, -3, 9, -8, 7, -6, 5, -4, 3, -2, 1)), 43.0)


if __name__ == "__main__":
    unittest.main()