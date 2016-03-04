package data;

import java.util.ArrayList;

import sort.ArticleDistanceCompare;

public class Cluster {
	private String cid;
	private Point centroid;
	private ArrayList<Article> articles = new ArrayList<Article>();
	private double distance = 0;

	public Cluster(String cid) {
		super();
		this.cid = cid;
	}
	
	public void sortArticle(Point userCentroid) {
		for(Article article : articles) {
			article.setDistance(Point.getDistance(article.getPoint(), userCentroid));
		}
		articles.sort(new ArticleDistanceCompare());
	}
	
	public int getSize() {
		return articles.size();
	}
	
	public void clear() {
		articles.clear();
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article) {
		articles.add(article);
	}
	

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getFarthestDistance() {
		double maxDist = 0;
		for(Article article : articles) {
			double dist = Point.getDistance(article.getPoint(), centroid);
			if(maxDist < dist) {
				maxDist = dist;
			}
		}
		return maxDist;
	}
	
	@Override
	public String toString() {
		String arti = "";
		double size = articles.size();
//		double mean = Math.sqrt(getSquaredError()/articles.size());
		for(int i = 0; i < (size * (8.0/10.0)) ; i++) {
			arti += articles.get(i).toString();
//			if(Point.getDistance(articles.get(i).getPoint(), centroid) > mean * 0.2 && getSquaredError() > 0.01) {
//				arti += "*";
//			}
			arti += "\n"; 
		}
		String centroid = "";
		for(int i = 0; i < this.centroid.getData().size(); i++) {
			if(i != 0) {
				centroid += ", ";
			}
			centroid += Integer.toString(i);
		}
		return "Cluster [cid=" + cid + "]\n" + arti + "=>" + getSquaredError();
	}
	
	public double getSquaredError() {
		double squaredError = 0;
		for(Article article : articles) {
			squaredError += Math.pow(Point.getDistance(article.getPoint(), centroid), 2);
		}
		return squaredError;
	}
}
