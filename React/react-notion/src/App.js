import { NotionAPI } from 'notion-client'
import "react-notion-x/src/styles.css";
import "prismjs/themes/prism-tomorrow.css";
import "katex/dist/katex.min.css";

import { Code } from "react-notion-x/build/third-party/code";
import { Collection } from "react-notion-x/build/third-party/collection";
import { Equation } from "react-notion-x/build/third-party/equation";
import { Modal } from "react-notion-x/build/third-party/modal";
import { Pdf } from "react-notion-x/build/third-party/pdf";
import Notion from "./Notion";

const notion = new NotionAPI();
const recordMap = await notion.getPage("eb1a447eda324c8da702597f7fb82ab1");

function App({ recordMap }) {
  return <Notion />;
}

export default App;
