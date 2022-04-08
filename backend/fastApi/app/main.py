from fastapi import FastAPI, responses

from app.routers import user_based_CF, related_show, keyword_result, tag_based_rec_show

app = FastAPI()

@app.get("/")
def main():
    return responses.RedirectResponse(url="/docs/")

app.include_router(user_based_CF.router)
app.include_router(related_show.router)
app.include_router(keyword_result.router)
app.include_router(tag_based_rec_show.router)