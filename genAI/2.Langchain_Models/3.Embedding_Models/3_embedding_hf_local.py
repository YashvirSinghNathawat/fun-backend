from langchain_huggingface import HuggingFaceEmbeddings
from dotenv import load_dotenv

load_dotenv()



embedding = HuggingFaceEmbeddings(model='sentence-transformers/all-MiniLM-L6-v2')

result = embedding.embed_query("Delhi is the capital of India.")

print(str(result))