//
//  MoneySimulationView.swift
//  money-simulation-ios
//
//  Created by Jacob Su on 10/11/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import UIKit

class MoneySimulationView: UIView {
    
    var moneys : [Int] = []
    
    override func draw(_ rect: CGRect) {
        let con = UIGraphicsGetCurrentContext()
        guard let ctx = con else {
            return
        }
        
        ctx.clear(rect)
        ctx.setFillColor(UIColor.white.cgColor)
        ctx.fill(rect)
        ctx.setStrokeColor(UIColor.red.cgColor)
        ctx.setLineWidth(4)
        ctx.stroke(self.bounds)
        ctx.setFillColor(UIColor.blue.cgColor)
        
        if (moneys.count == 0) {
            return
        }
        
        print("W, H = \(self.bounds.width), \(self.bounds.height)")
        let w = CGFloat(self.bounds.width) / CGFloat(moneys.count)
        let h = CGFloat(self.bounds.height)
        for (i, money) in moneys.enumerated() {
            ctx.fill(CGRect(x: CGFloat(i)*w + 1, y: h - CGFloat(money), width: w - 1, height: CGFloat(money)))
        }
    }
    
    func render(moneys : [Int]) -> Void {
        self.moneys = moneys.sorted()
        setNeedsDisplay()
    }
}
