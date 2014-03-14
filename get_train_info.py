#coding=UTF-8
import httplib, urllib, unittest

class SearchTrainCode(unittest.TestCase):
    #定义setUp()方法，该方法会在测试用例执行之前运行
    def setUp(self):
        #httplib.HTTPConnection(host, port)
        #host为测试服务器的域名，不用加协议名称，port为端口号，默认为80
        self.conn = conn = httplib.HTTPConnection('chepiao.sinaapp.com', 80)
    
    #定义tearDown()方法， 该方法会在用例完成后执行
    def tearDown(self):
        if self.conn is not None:
            self.conn.close()
    #要使用post方法提交，先定义了一个doPost()的方法
    def doPost(self, path='/', params={}):
        body = urllib.urlencode(params)        
        headers = {"Content-type": "application/x-www-form-urlencoded"}
        self.conn.request('POST', path, body, headers)
        return self.conn.getresponse()
    
    #定义测试方法：
    def test_get_train_info(self):
        #先准备测试要提交的数据 trainCode: 车次[字母+数字]
        #params必须为字典格式，此处查询2251次列车，该车从北京开往丹东
        params = {'trainCode': '2251'}
        url = '/api.php?act=code'
        #调用doPost()方法,此处的url是域名之后的部分，别忘了‘/’
        #使用response接收返回的数据
        response = self.doPost(url, params)
        self.assertEqual(response.status, httplib.OK, str(response.status)+response.reason)
        #将接收的数据转为字典，该例子中有单引号和双引号的问题， 为了简单，我直接使用eval(),没有使用json.loads()
        result = eval(response.read())
        self.assertEqual(result['item'][0][1], '北京', '始发站错误')
        self.assertEqual(result['item'][-1][1], '丹东', '终点站错误')
        
if __name__ == '__main__':
    unittest.main()
