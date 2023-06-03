from http.server import BaseHTTPRequestHandler, HTTPServer
from socketserver import ThreadingMixIn

from model_ml.predictor import predict_content

hostName = '0.0.0.0'
serverPort = 8080


class Handler(BaseHTTPRequestHandler):

    def do_POST(self):
        if self.path == '/analyze':
            self.send_response(200)
            self.send_header('Content-type', 'text/plain')
            self.end_headers()

            post_body = self.rfile.read(int(self.headers.get('Content-Length'))).decode()
            result = predict_content(post_body)
            self.wfile.write(result.encode())

            print('For content:', post_body, 'was result:', result)
        else:
            self.send_response(404)
            self.send_header('Content-type', 'text/plain')
            self.end_headers()
            self.wfile.write("Please, send POST request for path '/analyze'".encode())

    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/plain')
        self.end_headers()
        self.wfile.write("Please, send POST request for path '/analyze'".encode())


class ThreadedHTTPServer(ThreadingMixIn, HTTPServer):
    'Handle requests in a separate thread.'


if __name__ == '__main__':
    webServer = ThreadedHTTPServer((hostName, serverPort), Handler)
    print("Server started http://%s:%s" % (hostName, serverPort))
try:
    webServer.serve_forever()
except KeyboardInterrupt:
    pass

webServer.server_close()
print('Server stopped.')
