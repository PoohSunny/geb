/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javascript

import fixture.Browser
import fixture.DriveMethodSupportingSpecWithServer

class CallingMethodsSpec extends DriveMethodSupportingSpecWithServer {

    def "calling methods"() {
        given:
        server.html """
            // tag::html[]
            <html>
                <head>
                    <script type="text/javascript">
                        function addThem(a,b) {
                            return a + b;
                        }
                    </script>
                </head>
            </html>
            // end::html[]
        """

        expect:
        // tag::calling_methods[]
        Browser.drive {
            go "/"
            assert js.addThem(1, 2) == 3
        }
        // end::calling_methods[]
    }

    def "calling nested methods"() {
        given:
        server.html {
        }

        expect:
        // tag::nested_methods[]
        Browser.drive {
            go "/"
            js."document.write"("<html>Hello World!</html>")
            assert $().text() == "Hello World!"
        }
        // end::nested_methods[]
    }
}
