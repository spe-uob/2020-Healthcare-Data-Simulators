import boto3
import sys
import os

client = boto3.client('cognito-idp', region_name=sys.argv[2])
'''
example run:
python3 init-auth.py <ClientId> <Region> <USERNAME> <PASSWORD>
python3 init-auth.py 7n4vr35t6o5153456ervok1vm9 eu-west-2 data-sim-team jOvK-dRCs-kCW3-ZgPx
'''
response = client.initiate_auth(
    AuthFlow='USER_PASSWORD_AUTH',
    AuthParameters={
        'USERNAME': sys.argv[3],
        'PASSWORD': sys.argv[4]
    },

    ClientId=sys.argv[1]
)

sessionid = response['AuthenticationResult']['AccessToken']
print(sessionid)
