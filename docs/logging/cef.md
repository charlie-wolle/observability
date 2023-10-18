# CEF - Common Event Format

## Format description

2023-08-17 17:22.048 CEF:1|observability|observability test
backend|0.0.1-SNAPSHOT|1305|{username=matze} - JWT Token is generated.|3

| DATE | CEF:Version | Device Vendor   | Device Product | Device Version | Device Event Class ID | Name         | Severity | [Extension]                              |
|------|-------------|-----------------|----------------|----------------|-----------------------|--------------|----------|------------------------------------------|
| Date | CEF:1       | Customer name     | Project name   | 1.0.1          | 3001                  | Login Failed | 3        | usr=2571f48d-a302-46b0-8295-97b8b1f27a84 |

| Field                 | Description                                                |
|-----------------------|------------------------------------------------------------|
| Device Vendor         | Application / Customer name                                |
| Device Product        | Project name                                               |
| Device Version        | Application Version                                        |
| Device Event Class ID | unique identifier for each event-type                      |
| Name                  | human-readable and understandable description of the event |
| Severity              | reflects the importance of the event                       |
| [Extension]           | contains a collection of key-value pairs                   |

## Severity and Event Class ID mapping

- first digit: severity class
- second digit: exact severity
- rest: identifier

```
CRITICAL 9-10	
     9901 Access to a backend endpoint was blocked due to missing authentication
     9902 Access to a backend endpoint was blocked due to missing authorization
   
HIGH 	7- 8
     7701 Failed account log on
     7702 A user account was assigned to an admin role
     7803 A group was assigned to an admin role
     7804 BadCredentialException
     
        
MEDIUM 	4-6
     4501 A user account was created
     4502 A user account was deleted
     4503 A user account was assigned to a non-admin role
     4604 A group was created
     4605 A group was deleted
     4606 A group was assigned to a non-admin role
     4607 A configuration/ setting of the application value was changed
     4608 Repository exception

LOW 	0-3
     1201 Successful account log on
     1202 An account logged off
     1303 A user account attribute was changed
     1304 A group attribute was changed
     1305 Token generated
```

This is an extension of
the [Application Services EMEA Engineering Practices](https://softwareone.sharepoint.com/sites/a_emea_ts_appsvcs_engineering/SitePages/_Public/Engineering-Practices.aspx?xsdata=MDV8MDF8fDlkZTIzMTdjMWJjODQ1MDQyZmVmMDhkYjkxOWI4Y2VkfDFkYzliMzM5ZmFkYjQzMmU4NmRmNDIzYzM4YTBmY2I4fDB8MHw2MzgyNjM4Njk0NzMzNzAwOTV8VW5rbm93bnxWR1ZoYlhOVFpXTjFjbWwwZVZObGNuWnBZMlY4ZXlKV0lqb2lNQzR3TGpBd01EQWlMQ0pRSWpvaVYybHVNeklpTENKQlRpSTZJazkwYUdWeUlpd2lWMVFpT2pFeGZRPT18MXxMM1JsWVcxekx6RTVPamczT0RFMk1UQXhZV1kxTnpRM056ZGlNRGt5WmprMk5XSXlPREF5TmpZd1FIUm9jbVZoWkM1MFlXTjJNaTlqYUdGdWJtVnNjeTh4T1Rwak0yTmxNREl5TlRSa016RTBOamRtT1daa1lUUmtaR0V5TlRZM05USTBNVUIwYUhKbFlXUXVkR0ZqZGpJdmJXVnpjMkZuWlhNdk1UWTVNRGM1TURFME5qQTBNdz09fDQ3NWIwZDNkOTkyMDRjYjYyZmVmMDhkYjkxOWI4Y2VkfGZjOTFjYTU3YjYwYTQwYmFhMGEwM2FiMmIwOTUzM2Iw&sdata=RW51aTNrQUtsTEwzZGFZWHJhNjNIWXRlbERlS3UxbEhSMWJDM3N5TUFMND0%3D&ovuser=1dc9b339-fadb-432e-86df-423c38a0fcb8%2Cjan.wollschlaeger%40softwareone.com&OR=Teams-HL&CT=1692286237667#sd-d-h-2-threat-detection-readiness)
.
