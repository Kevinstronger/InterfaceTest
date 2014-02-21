#import httplib, json

map_json = {
    "results": [
        {
            "address_components": [
                {
                    "long_name": "5903",
                    "short_name": "5903",
                    "types": [
                        "street_number"
                    ]
                },
                {
                    "long_name": "Laurium Road",
                    "short_name": "Laurium Rd",
                    "types": [
                        "route"
                    ]
                },
                {
                    "long_name": "Montibello",
                    "short_name": "Montibello",
                    "types": [
                        "neighborhood",
                        "political"
                    ]
                },
                {
                    "long_name": "夏洛特",
                    "short_name": "夏洛特",
                    "types": [
                        "locality",
                        "political"
                    ]
                },
                {
                    "long_name": "1, Charlotte",
                    "short_name": "1, Charlotte",
                    "types": [
                        "administrative_area_level_3",
                        "political"
                    ]
                },
                {
                    "long_name": "梅克伦堡县",
                    "short_name": "梅克伦堡县",
                    "types": [
                        "administrative_area_level_2",
                        "political"
                    ]
                },
                {
                    "long_name": "北卡罗来纳州",
                    "short_name": "NC",
                    "types": [
                        "administrative_area_level_1",
                        "political"
                    ]
                },
                {
                    "long_name": "美国",
                    "short_name": "US",
                    "types": [
                        "country",
                        "political"
                    ]
                },
                {
                    "long_name": "28226",
                    "short_name": "28226",
                    "types": [
                        "postal_code"
                    ]
                }
            ],
            "formatted_address": "5903 Laurium Road, 夏洛特北卡罗来纳州 28226美国",
            "geometry": {
                "location": {
                    "lat": 35.121788,
                    "lng": -80.795041
                },
                "location_type": "ROOFTOP",
                "viewport": {
                    "northeast": {
                        "lat": 35.12313698029149,
                        "lng": -80.79369201970849
                    },
                    "southwest": {
                        "lat": 35.12043901970849,
                        "lng": -80.79638998029151
                    }
                }
            },
            "types": [
                "street_address"
            ]
        }
    ],
    "status": "OK"
}

path = ('/maps/api/geocode/json?address=5903+Laurium+Rd%2C+Charlotte%2C+NC'
	'&sensor=false')

connection = httplib.HTTPConnection('maps.googleapis.com')
connection.request('GET', path)
rawreply = connection.getresponse().read()
reply = json.loads(rawreply)
#print reply.keys()

print reply['results'][0].keys()

print reply['results'][0]['geometry']['location']
