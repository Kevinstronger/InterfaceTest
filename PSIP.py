#coding=UTF-8

import json, requests

def make_json_post(url,data):
    request_headers = {"Pragma:": "no-cache", "Cache-Control": "no-cache",
                   "Content-type": "text/xml;charset=utf-8"}
    
    json_data = json.dumps(data)
    response = requests.post(url, data=json_data, headers=request_headers)
  
    print response.text
    
if __name__ == '__main__':
    #AD
    data = {'sid':'ad', 'sn': u'赵', 'gn':u'理', 'py':'zhaoli'}
    
    #Mail
    #data={'sid': 'ex', 'acct':'zhaoli'}
    
    #Phone   
    #data={'sid': 'll', 'acct':'zhaoli'}
    url = 'http://172.30.84.23:8080/setacct/'
    make_json_post(url,data)
