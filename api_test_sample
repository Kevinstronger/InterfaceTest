#coding=UTF-8

import unittest, httplib, urllib, json

class ApiTestCase(unittest.TestCase):
    def setUp(self):
        self.conn = httplib.HTTPConnection('localhost', 5000)
    
    def tearDown(self):
        if self.conn is not None:
            self.conn.close()
            
    def doGet(self, path='/'):
        self.conn.request('GET', path)
        return self.conn.getresponse()
    
    def doPost(self, path='/', params={}):
        params = urllib.urlencode(params)
        headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "application/json"}
        self.conn.request('POST', path, params, headers)
        return self.conn.getresponse()
    
    #验证访问根路径是不是返回{'hello':'world'}的json数据
    #测试用例必须要以test开头
    def test_index(self):
        res = self.doGet('/')
        self.assertEqual(res.status, 200)
        data = json.loads(res.read())
        self.assertEqual(data['hello'], 'world')
        
    #添加用户后，通过查询接口来验证插入数据
    def test_add_user(self):
        params = {'name': 'jerry', 'age': '34', 'sex':'female'}
        response = self.doPost('/add', params)
        print response.reason
        self.assertEqual(response.status, 200)
        
        
        #get find user
        response = self.doGet('/find_user?name='+'jerry')
        self.assertEqual(response.status, 200)
        data = json.loads(response.read())
        self.assertEqual(data['name'], 'jerry')
        self.assertEqual(data['age'], 34)
        self.assertEqual(data['sex'], 'female')
        

if __name__ == '__main__':
    unittest.main()
        
