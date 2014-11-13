## CICS TG Client Blocker Exit

### Introduction
This repo provides the sample code to back up the [Restricting CICS TG client applications](https://www.ibm.com/developerworks/community/blogs/aimsupport/entry/restricting_cics_tg_client_applications?lang=en) blog post.

### Pre-reqs
* CICS Transaction Gatway V9.0 or later

### Build
The Java code needs to be compiled with the `ctgclient.jar` for the version of CICS TG being used. Once compiled the class file needs to be packaged into a jar file and added to the Gateway daemon configuration as described in the documentation.

### Notice
&copy; Copyright IBM Corporation 2014

### License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
