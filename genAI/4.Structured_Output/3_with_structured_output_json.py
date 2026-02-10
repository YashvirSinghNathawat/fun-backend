# Review -> LLM -> Summary + Sentiment

from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()

model = ChatOpenAI()

# schema
Review = {
  "title": "Review",
  "type": "object",
  "properties": {
    "key_themes": {
      "type": "array",
      "items": { "type": "string" },
      "description": "Write down all the key themes discussed in the review in a list"
    },
    "summary": {
      "type": "string",
      "description": "1 line review of the review"
    },
    "sentiment": {
      "type": "string",
      "enum": ["pos", "neg"],
      "description": "Return sentiment of the review either negative, positive or neutral"
    },
    "pros": {
      "type": ["array", "null"],
      "items": { "type": "string" },
      "description": "Write down all the pros inside the list"
    },
    "cons": {
      "type": ["array", "null"],
      "items": { "type": "string" },
      "description": "Write down all the cons inside the list"
    },
    "name": {
      "type": ["string", "null"],
      "description": "Write name of author",
    }
  },
  "required": ["key_themes", "summary", "sentiment"]
}


structured_model = model.with_structured_output(Review)

review = """iPhone 16: The Pro Features Reach the Masses
The iPhone 16 is the most significant "standard" update Apple has released in years. It effectively closes the gap between the base model and the Pro, making it a compelling choice for almost everyone.

Key Highlights:
The A18 Chip: Apple skipped a generation to ensure this phone is built for Apple Intelligence. It’s incredibly fast, handles AAA console games with ease, and is significantly more power-efficient.

Physical Upgrades: The Action Button replaces the mute switch, and the new Camera Control button offers a tactile way to snap photos and slide through settings like zoom and depth of field.

Camera Versatility: A new vertical layout supports Spatial Video. More importantly, the Ultrawide lens now supports Macro photography, finally letting you take super-close-up shots without a Pro badge.

Battery & Design: Expect better endurance and a move toward bold, saturated colors (the Ultramarine is a standout).

The Trade-off:
The display is still 60Hz, which feels dated compared to the Pro’s 120Hz "ProMotion" screen. If you don't mind the slightly less fluid scrolling, there’s very little reason to spend more.

The Bottom Line: If you're upgrading from an iPhone 14 or older, the iPhone 16 feels like a massive leap forward in both personality and power."""

result = structured_model.invoke(review)

print(result)
print(result.name)