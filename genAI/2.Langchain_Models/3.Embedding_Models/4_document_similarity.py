from langchain_openai import OpenAIEmbeddings
from dotenv import load_dotenv
from sklearn.metrics.pairwise import cosine_similarity

load_dotenv()

docs = [
    "Virat Kohli is one of the greatest batsmen of Indian cricket.",
    "Sachin Tendulkar is known as the God of Cricket in India.",
    "MS Dhoni led the Indian team to multiple ICC trophies.",
    "Rohit Sharma is famous for his elegant batting and big scores.",
    "Jasprit Bumrah is a key fast bowler for the Indian cricket team."
]

query = "Tell me about Sachin?"

embedding = OpenAIEmbeddings(model='text-embedding-3-large', dimensions = 300)

doc_embeddings = embedding.embed_documents(docs)
query_embeddings = embedding.embed_query(query)

result = cosine_similarity([query_embeddings], doc_embeddings)

index, score = sorted(list(enumerate(result)), key = lambda x:x[1])[-1]

print(docs[index])
print("Score : ", score)