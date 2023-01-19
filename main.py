# assume base calss is Point3D
class Point3D:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def getX(self, x):
        return self.x 
    
    def getY(self, y):
        return self.y
    
    def getZ(self, z):
        return self.z 

# Plane3D is a subclass of Point3D
class Plane3D(Point3D):
    def __init__(self, x, y, z, a, b, c):
        super().__init__(x, y, z)
        self.a = a
        self.b = b
        self.c = c

    def getA(self, a):
        return self.a
    
    def getB(self, b):
        return self.b
    
    def getC(self, c):
        return self.c

    
    def getDistance(self):
        return abs(self.a * self.x + self.b * self.y + self.c * self.z) / math.sqrt(self.a ** 2 + self.b ** 2 + self.c ** 2)




    
if __name__ == "__main__":
    plane = Plane3D(1, 2, 3, 4, 5, 6)
    print(plane.getDistance())


    