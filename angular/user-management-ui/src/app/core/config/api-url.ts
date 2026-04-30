export function getApiBaseUrl(): string {
  const { protocol, hostname } = window.location;

  if (hostname === 'localhost' || hostname === '127.0.0.1') {
    return 'http://localhost:8080';
  }

  if (hostname.includes('.app.github.dev')) {
    const backendHost = hostname.replace(/-4200\.app\.github\.dev$/, '-8080.app.github.dev');
    return `${protocol}//${backendHost}`;
  }

  return `${protocol}//${hostname}:8080`;
}