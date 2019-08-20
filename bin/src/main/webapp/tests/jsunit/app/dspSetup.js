var uri = '../../index.html';

function setUpPage() {
  setUpPageStatus = 'running';
  top.testManager.documentLoader.callback = setUpPageComplete;
  top.testManager.documentLoader.load(uri);
}

function setUpPageComplete() {
  if (setUpPageStatus == 'running') {
    setUpPageStatus = 'complete';
  }
}

function getTestPage() {
  let buffer = top.testManager.documentLoader.buffer();
  return { "window": buffer.window, "document": buffer.document };
}
