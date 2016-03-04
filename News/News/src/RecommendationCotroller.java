import java.sql.SQLException;
import java.util.ArrayList;

import kmean.KMean;
import recommendation.RecommendationModule;
import docWordMatrix.DocWordMatrixGenerator;


public class RecommendationCotroller {
	
	public ArrayList<String> recommend(String id) throws SQLException {
		long start = System.currentTimeMillis() ;
		if(id == null) { 
			System.out.println("사용법: 추천받고자 하는 유저의 아이디 넣어라잉~!");
			return null;
		}
		DocWordMatrixGenerator generator = new DocWordMatrixGenerator(id);
		generator.generate();
		System.out.println("***************************************************************");
		KMean articleKmean = new KMean(generator.getArticles(), (int) Math.floor(Math.sqrt((double) generator.getArticles().size() / 1.5)));
		articleKmean.init();
		articleKmean.calculate();
		articleKmean.printResult();
		System.out.println("***************************************************************");
		KMean userArticleKmean = new KMean(generator.getUserArticles());
		userArticleKmean.init();
		userArticleKmean.calculate();
		userArticleKmean.printResult();
		System.out.println("***************************************************************");
		RecommendationModule recommendationModule = new RecommendationModule(articleKmean.getClusters(), userArticleKmean.getClusters());
		recommendationModule.init();
		recommendationModule.recommend();
		long end = System.currentTimeMillis(); 
		System.out.println((end-start)/1000 +" 초 걸림");
		
		return recommendationModule.getResult();
	}
}
